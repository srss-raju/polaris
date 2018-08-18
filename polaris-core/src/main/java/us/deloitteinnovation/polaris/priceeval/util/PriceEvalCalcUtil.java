package us.deloitteinnovation.polaris.priceeval.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;
import us.deloitteinnovation.polaris.common.util.Constant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class PriceEvalCalcUtil {

    private static final Logger LOG = LoggerFactory
            .getLogger(PriceEvalCalcUtil.class);
    private static Integer customerId = 0;

    private PriceEvalCalcUtil() {
    }

    /**
     * @param priceEvaluatorVOList
     * @param groupByValue
     * @return JSONArray
     */
    public static JSONArray getPriceEvalDetails(
            List<PriceEvaluatorVO> priceEvaluatorVOList, String groupByValue) {
        int counter = 0;
        float originalValueSum = 0;
        JSONArray priceEvaluatorProducts = new JSONArray();
        JSONObject productDetailsObject = null;
        String previousProductName = null;
        PriceEvaluatorVO previousPriceEvaluatorVO = null;

        for (PriceEvaluatorVO priceEvaluatorVO : priceEvaluatorVOList) {
            if (!priceEvaluatorVO.getProductName().equals(previousProductName)) {
                if (previousProductName != null) {
                    fillJson(productDetailsObject, previousPriceEvaluatorVO,
                            groupByValue, priceEvaluatorProducts,
                            originalValueSum, counter);
                }
                counter = 0;
                originalValueSum = 0;
                productDetailsObject = new JSONObject();
                counter++;
                originalValueSum = originalValueSum
                        + priceEvaluatorVO.getOriginalValue();
            } else {
                counter++;
                originalValueSum = originalValueSum
                        + priceEvaluatorVO.getOriginalValue();
            }
            previousPriceEvaluatorVO = priceEvaluatorVO;
            previousProductName = priceEvaluatorVO.getProductName();
        }

            fillJson(productDetailsObject, previousPriceEvaluatorVO,
                    groupByValue, priceEvaluatorProducts, originalValueSum,
                    counter);

        return priceEvaluatorProducts;
    }

    @SuppressWarnings("unchecked")
    private static void fillJson(JSONObject productDetailsObject,
            PriceEvaluatorVO previousPriceEvaluatorVO, String groupByValue,
            JSONArray priceEvaluatorProducts, float originalValueSum,
            int counter) {
        productDetailsObject.put("customerId",
                previousPriceEvaluatorVO.getCustomerId());
        productDetailsObject.put("customerName",
                previousPriceEvaluatorVO.getCustomerName());
        if (Constant.PRODUCTATTRIBUTE1.equals(groupByValue)) {
            productDetailsObject.put(Constant.PRODUCTATTRIBUTE1,
                    previousPriceEvaluatorVO.getProductAttribute1());
        } else if (Constant.PRODUCTATTRIBUTE2.equals(groupByValue)) {
            productDetailsObject.put(Constant.PRODUCTATTRIBUTE2,
                    previousPriceEvaluatorVO.getProductAttribute2());
        } else if (Constant.PRODUCTATTRIBUTE3.equals(groupByValue)) {
            productDetailsObject.put(Constant.PRODUCTATTRIBUTE3,
                    previousPriceEvaluatorVO.getProductAttribute3());
        } else if (Constant.PRODUCTATTRIBUTE4.equals(groupByValue)) {
            productDetailsObject.put(Constant.PRODUCTATTRIBUTE4,
                    previousPriceEvaluatorVO.getProductAttribute4());
        } else if (Constant.PRODUCTATTRIBUTE5.equals(groupByValue)) {
            productDetailsObject.put(Constant.PRODUCTATTRIBUTE5,
                    previousPriceEvaluatorVO.getProductAttribute5());
        }
        productDetailsObject.put("productId",
                previousPriceEvaluatorVO.getProductId());
        productDetailsObject.put("productName",
                previousPriceEvaluatorVO.getProductName());
        productDetailsObject.put("externalSalesId",
                previousPriceEvaluatorVO.getExternalSalesId());
        productDetailsObject.put("shelfPrice", originalValueSum / counter);
        productDetailsObject.put("originalPrice", originalValueSum / counter);
        if (0 == Math.round(previousPriceEvaluatorVO.getSimulatedShelfPrice())) {
            previousPriceEvaluatorVO.setSimulatedShelfPrice(originalValueSum
                    / counter);
        }
        productDetailsObject.put("simulatedShelfPrice",
                previousPriceEvaluatorVO.getSimulatedShelfPrice());
        productDetailsObject
                .put("Change",
                        Math.round(((previousPriceEvaluatorVO
                                .getSimulatedShelfPrice() - (originalValueSum / counter)) / (originalValueSum / counter)) * Constant.PERCENTAGE));
        priceEvaluatorProducts.add(productDetailsObject);
    }

    /**
     * This method calculates simulated Lift and prepares updated products list
     * externalSalesList - received rows from database to update
     *
     * @param priceEvaluatorVOList
     *            - received from UI;
     * @param jdbcTemplate
     * @return priceEvaluatorUpdateVOList - these details will get updated in
     *         the external sales table with batch update statement in DAO
     */
    public static List<PriceEvaluatorVO> calculatePriceEvaluatorSimulation(
            List<PriceEvaluatorVO> priceEvaluatorVOList,
            JdbcTemplate jdbcTemplate) {
        LOG.info(" -----Entered PriceEvalCalcUtil : calculatePriceEvaluatorSimulation()-----");
        Set<Integer> simulatedProductIds = getSimulatedProductIds(priceEvaluatorVOList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("customerId", customerId);
        parameters.addValue("simulatedProductIds", simulatedProductIds);

        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(
                jdbcTemplate.getDataSource());
        List<PriceEvaluatorVO> externalSalesList = parameterJdbcTemplate.query(
                Constant.SQL_GET_EXTERNAL_SALES_AND_COEFFICIENTS, parameters,
                new PriceEvaluatorMapper());
        updateExternalSalesDetails(priceEvaluatorVOList, externalSalesList);
        return externalSalesList;
    }

    private static Set<Integer> getSimulatedProductIds(
            List<PriceEvaluatorVO> priceEvaluatorVOList) {

        Set<Integer> simulatedProductIds = new HashSet<>();
        for (PriceEvaluatorVO simulatedPriceEvaluatorVO : priceEvaluatorVOList) {
            simulatedProductIds.add(simulatedPriceEvaluatorVO.getProductId());
            customerId = simulatedPriceEvaluatorVO.getCustomerId();
        }
        return simulatedProductIds;
    }

    private static void updateExternalSalesDetails(
            List<PriceEvaluatorVO> priceEvaluatorVOList,
            List<PriceEvaluatorVO> externalSalesList) {
        int shelfPrice;
        double pricePercentage;
        double calcLift;
        for (PriceEvaluatorVO externalSalesVO : externalSalesList) {
            for (PriceEvaluatorVO priceEvaluatorSimulatedVO : priceEvaluatorVOList) {
                shelfPrice = (externalSalesVO.getActualPromoValue() + externalSalesVO.getActualNonPromoValue())
                           / ((externalSalesVO.getActualPromoUnits() + externalSalesVO.getActualNonPromoUnits()) != 0 ? externalSalesVO.getActualPromoUnits() + externalSalesVO.getActualNonPromoUnits() : 1);
                   pricePercentage = (priceEvaluatorSimulatedVO
                           .getSimulatedShelfPrice() - shelfPrice) / (shelfPrice != 0 ? shelfPrice : 1);
                   calcLift = externalSalesVO.getCoefficientValue().floatValue()
                           * pricePercentage
                           * externalSalesVO.getBaselinePromoUnits();
                   externalSalesVO
                           .setSimulatedShelfPrice((priceEvaluatorSimulatedVO
                                   .getProductId() == externalSalesVO
                                   .getProductId()) ? priceEvaluatorSimulatedVO
                                   .getSimulatedShelfPrice() : externalSalesVO
                                   .getSimulatedShelfPrice());
                   externalSalesVO.setSimulatedUnits(Math.round(calcLift));
                   externalSalesVO.setExternalSalesId(externalSalesVO
                           .getExternalSalesId());

            }
        }
    }

}

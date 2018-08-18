package us.deloitteinnovation.polaris.priceeval.dao.internal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.priceeval.dao.IPriceEvalDAO;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;
import us.deloitteinnovation.polaris.priceeval.util.PEJSONConvertor;
import us.deloitteinnovation.polaris.priceeval.util.PriceEvalCalcUtil;
import us.deloitteinnovation.polaris.priceeval.util.PriceEvaluatorMapper;
import us.deloitteinnovation.polaris.common.util.Constant;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Repository
public class PriceEvalDAOImpl implements IPriceEvalDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PriceEvalDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public JSONObject getPriceEvaluatorDetails(int customerId,String productLevel,String productLevelValue,String groupByValue) {
        StringBuilder queryBuilder = new StringBuilder(Constant.SQL_GET_PRICE_EVAL_DETAILS1);
        queryBuilder.append(Constant.SQL_GET_PRICE_EVAL_DETAILS2).append(productLevel).append("=").append("'").append(productLevelValue).append("'");
        queryBuilder.append(Constant.SQL_GET_PRICE_EVAL_DETAILS3);
        queryBuilder.append(Constant.SQL_GET_PRICE_EVAL_DETAILS4);
        queryBuilder.append(groupByValue).append(",");
        queryBuilder.append(Constant.SQL_GET_PRICE_EVAL_DETAILS5);
        List<PriceEvaluatorVO> priceEvalutorVOList =  jdbcTemplate.query(queryBuilder.toString(),new Integer[] {customerId},new PriceEvaluatorMapper());

        JSONArray priceEvalutorDetails = PriceEvalCalcUtil.getPriceEvalDetails(priceEvalutorVOList,groupByValue);
        return PEJSONConvertor.convert(priceEvalutorDetails,groupByValue);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public JSONObject savePriceEvaluatorDetails(List<PriceEvaluatorVO> priceEvaluatorVOList) {
        JSONObject saveResult = new JSONObject();
        List<PriceEvaluatorVO> priceEvaluatorUpdateVOList = PriceEvalCalcUtil.calculatePriceEvaluatorSimulation(priceEvaluatorVOList,jdbcTemplate);
            int[] updatedRowsCount = updateExternalSales(priceEvaluatorUpdateVOList);

            if (updatedRowsCount.length > 0) {
                saveResult.put("success", updatedRowsCount.length + " rows updated Successfully");
            } else {
                saveResult.put("success", "No Records to update");
            }
        return saveResult;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject getCustomers() {
        Map<String, Integer> customers = getCustomerDetails();
        JSONObject json = new JSONObject();
        json.putAll(customers);
        return json;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> getCustomerDetails() {
        return (Map<String, Integer>) jdbcTemplate.query(Constant.GET_CUSTOMERS, new ResultSetExtractor<Object>() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException {
                Map<String, Integer> customers = new HashMap<String, Integer>();
                while (rs.next()) {
                    customers.put( rs.getString("Customer_Name"), rs.getInt("ID"));
                }
                return customers;
            }
        });
    }

    /**
     * This method updates external sales table with simulated Shelf Price and simulated Units
     * @param priceEvaluatorUpdateVOList
     * @return updatedRowsCount
     */
    private int[] updateExternalSales(final List<PriceEvaluatorVO> priceEvaluatorUpdateVOList) {
        return jdbcTemplate.batchUpdate(Constant.SQL_SAVE_PRICE_EVAL_DETAILS, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PriceEvaluatorVO priceEvaluatorUpdateVO = priceEvaluatorUpdateVOList.get(i);
                ps.setDouble(1, priceEvaluatorUpdateVO.getSimulatedShelfPrice());
                ps.setLong(2,(long)((priceEvaluatorUpdateVO.getActualNonPromoUnits()+priceEvaluatorUpdateVO.getActualPromoUnits())*1.10));
                ps.setInt(3, (int) (priceEvaluatorUpdateVO.getSimulatedShelfPrice() * (priceEvaluatorUpdateVO.getActualNonPromoUnits()+priceEvaluatorUpdateVO.getActualPromoUnits())*1.10));
                ps.setInt(4, priceEvaluatorUpdateVO.getExternalSalesId());
            }
            @Override
            public int getBatchSize() {
                return priceEvaluatorUpdateVOList.size();
            }
          });
    }
}

package us.deloitteinnovation.polaris.priceeval.util;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;

/**
 * @author RajeshKumar B
 *
 */
public class PriceEvaluatorMapper implements RowMapper<PriceEvaluatorVO> {
    @Override
    public PriceEvaluatorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PriceEvaluatorVO priceEvaluatorVO = new PriceEvaluatorVO();
        priceEvaluatorVO.setCustomerId(rs.getInt("Customer_ID"));
        priceEvaluatorVO.setProductId(rs.getInt("Product_ID"));
        priceEvaluatorVO.setCustomerName(rs.getString("Customer_Name"));
        priceEvaluatorVO.setProductName(rs.getString("Product_Name"));
        priceEvaluatorVO.setProductAttribute1(rs.getString("Product_Attribute_1"));
        priceEvaluatorVO.setProductAttribute2(rs.getString("Product_Attribute_2"));
        priceEvaluatorVO.setProductAttribute3(rs.getString("Product_Attribute_3"));
        priceEvaluatorVO.setProductAttribute4(rs.getString("Product_Attribute_4"));
        priceEvaluatorVO.setProductAttribute5(rs.getString("Product_Attribute_5"));
        priceEvaluatorVO.setOriginalValue(rs.getFloat("POS_Actual_Average_Price"));
        priceEvaluatorVO.setSimulatedPrice(new BigDecimal(rs.getInt("Sim_Value"))); // Check this
        priceEvaluatorVO.setSimulatedShelfPrice(rs.getDouble("Sim_Shelf_Price")); // Check this
        priceEvaluatorVO.setSimulatedUnits(rs.getInt("Sim_Units"));
        priceEvaluatorVO.setExternalSalesId(rs.getInt("External_Sales_ID"));
        priceEvaluatorVO.setActualPromoValue(rs.getInt("POS_Actual_Promo_Value"));
        priceEvaluatorVO.setActualNonPromoValue(rs.getInt("POS_Actual_Non_Promo_Value"));
        priceEvaluatorVO.setActualPromoUnits(rs.getInt("POS_Actual_Promo_Units"));
        priceEvaluatorVO.setActualNonPromoUnits(rs.getInt("POS_Actual_Non_Promo_Units"));
        priceEvaluatorVO.setBaselinePromoUnits(rs.getInt("POS_Baseline_Promo_Units"));
        priceEvaluatorVO.setCoefficientValue(rs.getBigDecimal("Coefficient_Value"));

        return priceEvaluatorVO;
    }
}

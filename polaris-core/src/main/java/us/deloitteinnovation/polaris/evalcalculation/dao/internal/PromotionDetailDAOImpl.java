package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.util.Constant;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionDetailDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromoEffectivenessWhen;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionDetail;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */

@Repository
public class PromotionDetailDAOImpl implements IPromotionDetailDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PromotionDetailDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PromotionDetail getPromotionDetailByPromotionId(int promotionId) {
        return jdbcTemplate.query(GET_PROMOTION_DETAIL_BY_PROMO_ID, new Object[]{promotionId}, rs -> {
           if (rs.next()){
               PromotionDetail promotionDetail = new PromotionDetail();
               promotionDetail.setPromotionId(promotionId);
               promotionDetail.setPromotionName(rs.getString("Promo_Campaign_Name"));
               promotionDetail.setProductId(rs.getInt("Product_ID"));
               promotionDetail.setProductName(rs.getString("Product_Name"));
               promotionDetail.setCustomerId(rs.getInt("Customer_ID"));
               promotionDetail.setCustomerName(rs.getString("Customer_Name"));
               promotionDetail.setDate(rs.getString("Promo_Start_Date"));
               promotionDetail.setPromotionType(rs.getString("Promo_Type"));
               promotionDetail.setPromotionTactic(rs.getString("Promo_Tactic"));
               promotionDetail.setPromotionPrice(rs.getDouble("Promo_Actual_Price"));
               promotionDetail.setNonPromotionPrice(rs.getDouble("Shelf_Price"));
               promotionDetail.setOriginalVolume(rs.getDouble("Promo_Actual_Units"));
               return promotionDetail;
           }
            return null;
        });
    }

    @Override
    public List<PromoEffectivenessWhen> getPromoEffectivenessWhen(Integer offSet, Integer pageSize) {
        int _offSet = (offSet < 1) ? Constant.DEFAULT_OFFSET : offSet;
        int _size = (pageSize < 1) ? Constant.DEFAULT_SIZE : pageSize;
        return jdbcTemplate.query(GET_PROMO_EFFECTIVENESS_WHEN, new Object[]{_offSet, _size}, new BeanPropertyRowMapper<>(PromoEffectivenessWhen.class));
    }
    
    //queries:
    private static final String GET_PROMOTION_DETAIL_BY_PROMO_ID =
            "SELECT  [Promo_ID],[Promo_Campaign_Name],[Product_ID],[Product_Name],[Customer_ID], [Customer_Name],[Promo_Start_Date],[Promo_Actual_Units],[Promo_Type],[Promo_Tactic],[Shelf_Price] ,[Promo_Actual_Price] " +
                    "FROM [dbo].[cvw_Promotion_Effectiveness_One] WHERE [Promo_ID] = ?";

    private static final String GET_PROMO_EFFECTIVENESS_WHEN =
                    "with promo_effectiveness_when_list as  " +
                            "(SELECT row_number() over(order by product_name,[Customer_Name] desc) as rownumber " +
                            "      ,[Customer_Name] " +
                            "      ,[Customer_Level_1] " +
                            "      ,[Customer_Level_2] " +
                            "      ,[Customer_Level_3] " +
                            "      ,[Customer_Level_4] " +
                            "      ,[Customer_Level_5] " +
                            "      ,[Customer_Attribute_1] " +
                            "      ,[Customer_Attribute_2] " +
                            "      ,[Customer_Attribute_3] " +
                            "      ,[Customer_Attribute_4] " +
                            "      ,[Customer_Attribute_5] " +
                            "      ,[Customer_Location_1] " +
                            "      ,[Customer_Location_2] " +
                            "      ,[Customer_Location_3] " +
                            "      ,[Customer_Location_4] " +
                            "      ,[Customer_Location_5] " +
                            "      ,[Product_Name] " +
                            "      ,[Product_Level_1] " +
                            "      ,[Product_Level_2] " +
                            "      ,[Product_Level_3] " +
                            "      ,[Product_Level_4] " +
                            "      ,[Product_Level_5] " +
                            "      ,[Product_Level_6] " +
                            "      ,[Product_Level_7] " +
                            "      ,[Product_Attribute_1] " +
                            "      ,[Product_Attribute_2] " +
                            "      ,[Product_Attribute_3] " +
                            "      ,[Product_Attribute_4] " +
                            "      ,[Product_Attribute_5] " +
                            "      ,[Promo_Campaign_ID] " +
                            "      ,[Product_ID] " +
                            "      ,[Promo_Start_Date] " +
                            "      ,[Promo_End_Date] " +
                            "      ,[Promo_Type] " +
                            "      ,[Promo_Tactic] " +
                            "      ,[Shelf_Price] " +
                            "      ,[Promo_Actual_Price] " +
                            "      ,[Promo_Baseline_Value] " +
                            "      ,[Promo_Actual_Units] " +
                            "      ,[Promo_Actual_Value] " +
                            "      ,[Promo_Actual_Gross_Margin_Value] " +
                            "      ,[Promo_Actual_Gross_Margin_Percent] " +
                            "      ,[Promo_Actual_Net_Revenue_Value] " +
                            "      ,[Promo_Actual_Incremental_Value] " +
                            "      ,[Promo_Actual_Roi] " +
                            "      ,[Promo_Actual_Cost] " +
                            "      ,[Promo_Latest_Estimate_Gross_Margin_Percent] " +
                            "      ,[Promo_Latest_Estimate_Cost] " +
                            "      ,[Promo_Latest_Estimate_Value] " +
                            "      ,[Promo_Latest_Estimate_Net_Revenue_Value] " +
                            "      ,[Promo_Latest_Estimate_Roi] " +
                            "      ,[Promo_Spend_Type] " +
                            "      ,[Promo_Spend_Rate] " +
                            "      ,[Promo_Campaign_Name] " +
                            "      ,[Promo_Retailer_Margin_Value] " +
                            "      ,[Promo_Standard_Margin_Value] " +
                            "      ,[Promo_Campaign_Holiday] " +
                            "      ,[Promo_Campaign_Description] " +
                            "      ,[Promo_Baseline_Units] " +
                            "      ,[Promo_Latest_Estimate_Incremental_Units] " +
                            "      ,[Gross_Margin] " +
                            "      ,[Gross_Margin_Percent] " +
                            "      ,[Cost_Element_6] " +
                            "      ,[Pillar_2] " +
                            "      ,[Cost_Element_7] " +
                            "      ,[Cost_Element_10] " +
                            "      ,[Pillar_3] " +
                            "      ,[Cost_Element_11] " +
                            "      ,[Gross_Revenue] " +
                            "      ,[Net_Revenue] " +
                            "      ,[Promo_Name] " +
                            "      ,[POS_Actual_Eq_Volume] " +
                            "      ,[POS_Baseline_Non_Promo_Units] " +
                            "      ,[POS_Baseline_Promo_Units] " +
                            "      ,[POS_Actual_Promo_Units] " +
                            "      ,[Competitor_Flag] " +
                            "  FROM [dbo].[cvw_Promo_Effectiveness_When]) " +
                            "  select * from promo_effectiveness_when_list " +
                            "  where rownumber >= ? and rownumber <= ? ";
}

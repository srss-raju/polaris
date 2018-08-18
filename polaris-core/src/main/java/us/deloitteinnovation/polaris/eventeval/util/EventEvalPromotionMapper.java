package us.deloitteinnovation.polaris.eventeval.util;

import org.springframework.jdbc.core.RowMapper;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class EventEvalPromotionMapper implements RowMapper<EventEvalPromotionVO> {
    @Override
    public EventEvalPromotionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventEvalPromotionVO eOptimPromotionVO = new EventEvalPromotionVO();
        eOptimPromotionVO.setProductId(rs.getInt("Product_ID"));
        eOptimPromotionVO.setPromotionActualCost(rs.getInt("Promo_Actual_Cost"));
        eOptimPromotionVO.setPromotionActualUnits(rs.getInt("Promo_Actual_Units"));
        eOptimPromotionVO.setPromotionBaselineUnits(rs.getInt("Promo_Baseline_Units"));
        eOptimPromotionVO.setPromotionCampaignCode(rs.getInt("Promo_Campaign_ID"));
        eOptimPromotionVO.setActualEndDate(rs.getDate("Promo_End_Date"));
        eOptimPromotionVO.setPromotionPlannedCost(rs.getFloat("Promo_Planned_Cost"));
        eOptimPromotionVO.setPromotionPlannedUnits(rs.getInt("Promo_Planned_Units"));
        eOptimPromotionVO.setActualPromotionPrice(rs.getFloat("Promo_Price"));
        eOptimPromotionVO.setPromotionSpendType(rs.getString("Promo_Spend_Type"));
        eOptimPromotionVO.setActualStartDate(rs.getDate("Promo_Start_Date"));
        eOptimPromotionVO.setPromotionTactic(rs.getString("Promo_Tactic"));
        eOptimPromotionVO.setPromotionType(rs.getString("Promo_Type"));
        eOptimPromotionVO.setSimulatedPromotionPrice(rs.getFloat("Sim_Promo_Price"));
        eOptimPromotionVO.setSimulatedEndDate(rs.getDate("Sim_Promo_End_Date"));
        eOptimPromotionVO.setSimulatedStartDate(rs.getDate("Sim_Promo_Start_Date"));
        eOptimPromotionVO.setSimulatedPromotionTactic(rs.getString("Sim_Promo_Tactic"));
        eOptimPromotionVO.setSimulatedPromotionUnits(rs.getInt("Sim_Promo_Units"));
        if(rs.getString("Sim_Volume_Lift_Coeffecient") != null){
            eOptimPromotionVO.setSimulatedVolumeLiftCoefficient(rs.getFloat("Sim_Volume_Lift_Coeffecient"));
        }else{
            eOptimPromotionVO.setSimulatedVolumeLiftCoefficient(0.0f);
        }

        eOptimPromotionVO.setCustomerId(rs.getInt("Customer_ID"));
        eOptimPromotionVO.setTacticCoupon(rs.getFloat("Tactic_Coupon"));
        eOptimPromotionVO.setTacticDisplay(rs.getFloat("Tactic_Display"));
        eOptimPromotionVO.setTacticTV(rs.getFloat("Tactic_TV"));
        eOptimPromotionVO.setDayDifference(rs.getInt("Days_Difference"));
        eOptimPromotionVO.setDepth(rs.getFloat("Depth"));
        eOptimPromotionVO.setSimulated(rs.getBoolean("Simulated"));
        eOptimPromotionVO.setProductListPrice(rs.getFloat("Product_List_Price"));
        eOptimPromotionVO.setTacticOne(rs.getFloat("Tactic_1"));
        eOptimPromotionVO.setTacticTwo(rs.getFloat("Tactic_2"));
        eOptimPromotionVO.setTacticThree(rs.getFloat("Tactic_3"));
        eOptimPromotionVO.setTacticFour(rs.getFloat("Tactic_4"));
        eOptimPromotionVO.setTacticFive(rs.getFloat("Tactic_5"));
        eOptimPromotionVO.setTacticSix(rs.getFloat("Tactic_6"));
        eOptimPromotionVO.setTacticSeven(rs.getFloat("Tactic_7"));
        eOptimPromotionVO.setTacticEight(rs.getFloat("Tactic_8"));
        return eOptimPromotionVO;
    }
}

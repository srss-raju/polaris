package us.deloitteinnovation.polaris.eventeval.util;

import java.util.HashMap;
import java.util.Map;

import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.SimulatedPromotionDetailsVO;
import us.deloitteinnovation.polaris.eventeval.model.SimulatedPromotionDetailsVO.SimulatedPromotionDetailsVOBuilder;
import us.deloitteinnovation.polaris.common.util.Constant;

import com.google.gson.Gson;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class EventEvalCalcUtil {

    private EventEvalCalcUtil(){
    }

    /**
     * @param eOptimPromotionVO
     * @return String
     */
    public static String getEventOptimizerPromoDetails(EventEvalPromotionVO eOptimPromotionVO) {
        /********************************************Calculate Actual Section Formulae************************************************/
        float promotionBaselineValue = eOptimPromotionVO.getPromotionBaselineUnits() * eOptimPromotionVO.getProductListPrice();
        //Actual Revenue
        float promotionActualValue = eOptimPromotionVO.getPromotionActualUnits() * eOptimPromotionVO.getActualPromotionPrice();
        //Actual Margin
        float actualMargin = ( (promotionActualValue - eOptimPromotionVO.getPromotionActualCost()) / eOptimPromotionVO.getPromotionActualCost() )*Constant.PERCENTAGE;
        //Actual Lift
        float promotionActualIncrementalValue = (eOptimPromotionVO.getPromotionActualUnits() * eOptimPromotionVO.getActualPromotionPrice()) - promotionBaselineValue;
        //Actual ROI
        float actualROI = (promotionActualIncrementalValue - eOptimPromotionVO.getPromotionActualCost()) / eOptimPromotionVO.getPromotionActualCost();
        //depth
        float actualDepth = ((eOptimPromotionVO.getProductListPrice()-eOptimPromotionVO.getActualPromotionPrice())/eOptimPromotionVO.getProductListPrice())*Constant.PERCENTAGE;
        /*****************************************************************************************************************************/
        eOptimPromotionVO.setActualRevenue(promotionActualValue);
        eOptimPromotionVO.setActualMargin(actualMargin);
        eOptimPromotionVO.setActualROI(actualROI);
        eOptimPromotionVO.setActualLift(promotionActualIncrementalValue);
        eOptimPromotionVO.setActualDepth(actualDepth);

        if(!eOptimPromotionVO.isSimulated()){
            
            fillSimulatedPromotionDetails(
                    eOptimPromotionVO,
                    new SimulatedPromotionDetailsVO.SimulatedPromotionDetailsVOBuilder()
                            .simulatedPromotionUnits(eOptimPromotionVO.getPromotionActualUnits())
                            .simulatedPrmotionDiscountDepth(0f)
                            .simulatedPromotionPrice(eOptimPromotionVO.getActualPromotionPrice())
                            .simulatedPromotionValue(promotionActualValue)
                            .simulatedPromotionIncrementalValue(promotionActualIncrementalValue)
                            .simulatedPromotionIncrementalUnits(eOptimPromotionVO.getPromotionActualUnits())
                            .simulatedROI(actualROI)
                            .simulatedMargin(actualMargin)
                            .simulatedVolumeLiftCoefficient(0f)
                            .simulatedDepth(actualDepth)
                            .tactic(eOptimPromotionVO.getPromotionTactic()));
        }else{
            /********************************************Calculate Simluated Section Formulae************************************************/
            //Simulated Revenue
            float simulatedPromotionValue = eOptimPromotionVO.getSimulatedPromotionUnits() * eOptimPromotionVO.getSimulatedPromotionPrice();
            //Simulated Margin
            float simulatedMargin = ((simulatedPromotionValue - eOptimPromotionVO.getPromotionActualCost()) / eOptimPromotionVO.getPromotionActualCost())*Constant.PERCENTAGE;
            //Simulated Lift
            float simulatedPromotionIncrementalValue = simulatedPromotionValue - promotionBaselineValue;
            //Simulated ROI
            float simulatedROI = (simulatedPromotionIncrementalValue - eOptimPromotionVO.getPromotionActualCost()) / eOptimPromotionVO.getPromotionActualCost();
            //Simulated Promotion Price
            float simulatedPromotionPrice = eOptimPromotionVO.getSimulatedPromotionPrice();
            float simulatedPrmotionDiscountDepth = (eOptimPromotionVO.getProductListPrice() - simulatedPromotionPrice )/eOptimPromotionVO.getProductListPrice();
            int simulatedPromotionUnits = Math.round(eOptimPromotionVO.getSimulatedVolumeLiftCoefficient()) * eOptimPromotionVO.getPromotionBaselineUnits();
            int simulatedPromotionIncrementalUnits = simulatedPromotionUnits - eOptimPromotionVO.getPromotionBaselineUnits();
            /*****************************************************************************************************************************/
            fillSimulatedPromotionDetails(
                    eOptimPromotionVO,
                    new SimulatedPromotionDetailsVO.SimulatedPromotionDetailsVOBuilder()
                            .simulatedPromotionUnits(simulatedPromotionUnits)
                            .simulatedPrmotionDiscountDepth(simulatedPrmotionDiscountDepth)
                            .simulatedPromotionPrice(simulatedPromotionPrice)
                            .simulatedPromotionValue(simulatedPromotionValue)
                            .simulatedPromotionIncrementalValue(simulatedPromotionIncrementalValue)
                            .simulatedPromotionIncrementalUnits(simulatedPromotionIncrementalUnits)
                            .simulatedROI(simulatedROI)
                            .simulatedMargin(simulatedMargin)
                            .simulatedVolumeLiftCoefficient(eOptimPromotionVO.getSimulatedVolumeLiftCoefficient())
                            .simulatedDepth(simulatedPrmotionDiscountDepth)
                            .tactic(eOptimPromotionVO.getPromotionTactic()));
                   }
        return convertVOtoJSON(eOptimPromotionVO);
    }

    /**
     * @param eOptimPromotionVO
     * @param eventEvalSimulatedPromotionVO
     * @return String
     */
    public static String getEventOptimizerSimulatedPromoDetails(EventEvalPromotionVO eOptimPromotionVO, EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO) {

        float tacticValue  = getTacticValue(eOptimPromotionVO, eventEvalSimulatedPromotionVO.getTactic());
        float simulatedVolumeLiftCoefficient = (eventEvalSimulatedPromotionVO.getTacticFlag() * tacticValue)
                + (eventEvalSimulatedPromotionVO.getDepthFlag() * (eventEvalSimulatedPromotionVO.getDepthValue()) * eOptimPromotionVO.getDepth())
                + (eventEvalSimulatedPromotionVO.getDaysFlag() * eventEvalSimulatedPromotionVO.getDaysValue() * eOptimPromotionVO.getDayDifference());
        float promotionBaselineValue = eOptimPromotionVO.getPromotionBaselineUnits() * eOptimPromotionVO.getProductListPrice();
        int simulatedPromotionUnits = eOptimPromotionVO.getPromotionBaselineUnits() * Math.round(simulatedVolumeLiftCoefficient);
        float simulatedPrmotionDiscountDepth = eventEvalSimulatedPromotionVO.getDepthValue();
        float simulatedPromotionPrice = eOptimPromotionVO.getProductListPrice() - (eOptimPromotionVO.getProductListPrice() * simulatedPrmotionDiscountDepth);
        float simulatedPromotionValue = simulatedPromotionUnits * simulatedPromotionPrice;
        float simulatedPromotionIncrementalValue = simulatedPromotionValue - promotionBaselineValue;
        int simulatedPromotionIncrementalUnits = simulatedPromotionUnits - eOptimPromotionVO.getPromotionBaselineUnits();
        float simulatedROI = (simulatedPromotionIncrementalValue - eOptimPromotionVO.getPromotionActualCost())/eOptimPromotionVO.getPromotionActualCost();
        float simulatedMargin =((simulatedPromotionValue - eOptimPromotionVO.getPromotionActualCost()) / eOptimPromotionVO.getPromotionActualCost()) * Constant.PERCENTAGE;

        fillSimulatedPromotionDetails(
                eOptimPromotionVO,
                new SimulatedPromotionDetailsVO.SimulatedPromotionDetailsVOBuilder()
                        .simulatedPromotionUnits(simulatedPromotionUnits)
                        .simulatedPrmotionDiscountDepth(simulatedPrmotionDiscountDepth)
                        .simulatedPromotionPrice(simulatedPromotionPrice)
                        .simulatedPromotionValue(simulatedPromotionValue)
                        .simulatedPromotionIncrementalValue(simulatedPromotionIncrementalValue)
                        .simulatedPromotionIncrementalUnits(simulatedPromotionIncrementalUnits)
                        .simulatedROI(simulatedROI)
                        .simulatedMargin(simulatedMargin)
                        .simulatedVolumeLiftCoefficient(simulatedVolumeLiftCoefficient)
                        .simulatedDepth(eventEvalSimulatedPromotionVO.getDepthValue())
                        .tactic(eventEvalSimulatedPromotionVO.getTactic()));
        return convertVOtoJSON(eOptimPromotionVO);
    }


    private static float getTacticValue(EventEvalPromotionVO eOptimPromotionVO, String tactic) {
    	Map<String,Float> tacticValueMap = new HashMap<String,Float>();
    	tacticValueMap.put(Constant.TACTIC1, eOptimPromotionVO.getTacticOne());
    	tacticValueMap.put(Constant.TACTIC2, eOptimPromotionVO.getTacticTwo());
    	tacticValueMap.put(Constant.TACTIC3, eOptimPromotionVO.getTacticThree());
    	tacticValueMap.put(Constant.TACTIC4, eOptimPromotionVO.getTacticFour());
    	tacticValueMap.put(Constant.TACTIC5, eOptimPromotionVO.getTacticFive());
    	tacticValueMap.put(Constant.TACTIC6, eOptimPromotionVO.getTacticSix());
    	tacticValueMap.put(Constant.TACTIC7, eOptimPromotionVO.getTacticSeven());
    	tacticValueMap.put(Constant.TACTIC8, eOptimPromotionVO.getTacticEight());
    	return (tacticValueMap.get(tactic)!=null) ? tacticValueMap.get(tactic) : 0;
         
    }

    private static void fillSimulatedPromotionDetails(EventEvalPromotionVO eventEvalPromoDetailsVO, SimulatedPromotionDetailsVOBuilder builder) {
        SimulatedPromotionDetailsVO vo = builder.build();
        eventEvalPromoDetailsVO.setSimulatedPromotionUnits(vo.getSimulatedPromotionUnits());
        eventEvalPromoDetailsVO.setSimulatedPromotionDiscountDepth(vo.getSimulatedDepth());
        eventEvalPromoDetailsVO.setSimulatedPromotionPrice(vo.getSimulatedPromotionPrice());
        eventEvalPromoDetailsVO.setSimulatedRevenue(vo.getSimulatedPromotionValue());
        eventEvalPromoDetailsVO.setSimulatedLift(vo.getSimulatedPromotionIncrementalValue());
        eventEvalPromoDetailsVO.setSimulatedPromotionIncrementalUnits(vo.getSimulatedPromotionIncrementalUnits());
        eventEvalPromoDetailsVO.setSimulatedROI(vo.getSimulatedROI());
        eventEvalPromoDetailsVO.setSimulatedMargin(vo.getSimulatedMargin());
        eventEvalPromoDetailsVO.setSimulatedVolumeLiftCoefficient(vo.getSimulatedVolumeLiftCoefficient());
        eventEvalPromoDetailsVO.setSimulatedDepth(Math.round(vo.getSimulatedDepth()));
        eventEvalPromoDetailsVO.setSimulatedPromotionTactic(vo.getTactic());
    }

    private static String convertVOtoJSON(EventEvalPromotionVO eventEvalPromoDetailsVO){
        Gson gson = new Gson();
        return gson.toJson(eventEvalPromoDetailsVO);
    }

}

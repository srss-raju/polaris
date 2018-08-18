package us.deloitteinnovation.polaris.evaluator.calculation.utils;

import us.deloitteinnovation.polaris.evaluator.calculation.model.ProductCoefficient;

/**
 * Created by rbentaarit on 7/12/2017.
 */
public class CalculationUtil {

    public static Double calculateTotalVolume (ProductCoefficient pCoef, Double nonPromotionPrice, Double promotionPrice){
        Double promoPriceLog = Math.log(promotionPrice);
        Double nonPromoPriceLog = Math.log(nonPromotionPrice);
        return Math.exp(pCoef.getIntercept() * 1
                + pCoef.getCoefNonPromoPrice() * nonPromoPriceLog
                + pCoef.getCoefACV() * 1 //Apercentage ACV (all-commodity volume)
                + pCoef.getCoefSeasonality() * 1 //seasonality
                + pCoef.getCoefDiscount() * (nonPromotionPrice/promotionPrice)
                + pCoef.getCoefPromoPrice() * promoPriceLog
                + pCoef.getCoefType1() * 1 // type1
                + pCoef.getCoefType2() * 1// type2
                + pCoef.getCoefType3() * 1//type3
                + pCoef.getCoefType4() * 1//type4
                + pCoef.getCoefType5() * 1//type5
                + pCoef.getCoefTactic1() * 1// tactic1
                + pCoef.getCoefTactic2() * 1//tactic2
                + pCoef.getCoefTactic3() * 1//tactic3
                + pCoef.getCoefTactic4() * 1//tactic4
                + pCoef.getCoefTactic5() * 1//tactic5
                + pCoef.getCoefTactic6() * 1//tactic6
                + pCoef.getCoefTactic7() * 1//tactic7
                + pCoef.getCoefTactic8() * 1//tactic8
                + pCoef.getCoefTactic9() * 1//tactic9
                + pCoef.getCoefTactic10() * 1//tactic10
                + pCoef.getCoefCompetitorNonPromo1() * 1//
                + pCoef.getCoefCompetitorNonPromo2() * 1//
                + pCoef.getCoefCompetitorNonPromo3() * 1//
                + pCoef.getCoefCompetitorNonPromo4() * 1//
                + pCoef.getCoefCompetitorNonPromo5() * 1//
                + pCoef.getCoefCompetitorPromo1() * 1//
                + pCoef.getCoefCompetitorPromo2() * 1//
                + pCoef.getCoefCompetitorPromo3() * 1//
                + pCoef.getCoefCompetitorPromo4() * 1//
                + pCoef.getCoefCompetitorPromo5() * 1//
                + pCoef.getCoefCompetitorAverageNonPromo() * 1//
                + pCoef.getCoefCompetitorAveragePromo() * 1//
        );
    }

    public static  Double calculateBaselineVolume(ProductCoefficient pCoef, Double nonPromotionPrice){
        Double nonPromoPriceLog = Math.log(nonPromotionPrice);
        return Math.exp(pCoef.getIntercept() * 1
                + pCoef.getCoefNonPromoPrice() * nonPromoPriceLog
                + pCoef.getCoefACV() * 1 //ACV
                + pCoef.getCoefSeasonality() * 1 //
                + pCoef.getCoefDiscount() * 1
                + pCoef.getCoefPromoPrice() * nonPromoPriceLog);
    }
}

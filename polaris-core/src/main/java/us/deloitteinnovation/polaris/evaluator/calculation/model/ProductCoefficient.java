package us.deloitteinnovation.polaris.evaluator.calculation.model;

/**
 * Created by rbentaarit on 7/12/2017.
 */
public class ProductCoefficient {

    private Integer id;
    private Integer productId;
    private Integer customerId;
    private Float intercept;
    private Float coefNonPromoPrice;
    private Float coefACV;
    private Float coefSeasonality;
    private Float coefDiscount;
    private Float coefPromoPrice;
    private Float coefType1;
    private Float coefType2;
    private Float coefType3;
    private Float coefType4;
    private Float coefType5;
    private Float coefTactic1;
    private Float coefTactic2;
    private Float coefTactic3;
    private Float coefTactic4;
    private Float coefTactic5;
    private Float coefTactic6;
    private Float coefTactic7;
    private Float coefTactic8;
    private Float coefTactic9;
    private Float coefTactic10;
    private Float coefCompetitorNonPromo1;
    private Float coefCompetitorNonPromo2;
    private Float coefCompetitorNonPromo3;
    private Float coefCompetitorNonPromo4;
    private Float coefCompetitorNonPromo5;
    private Float coefCompetitorPromo1;
    private Float coefCompetitorPromo2;
    private Float coefCompetitorPromo3;
    private Float coefCompetitorPromo4;
    private Float coefCompetitorPromo5;
    private Float coefCompetitorAverageNonPromo;
    private Float coefCompetitorAveragePromo;
    private String substituteLevel;
    private String mapeEst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Float getIntercept() {
        return intercept;
    }

    public void setIntercept(Float intercept) {
        this.intercept = intercept;
    }

    public Float getCoefNonPromoPrice() {
        return coefNonPromoPrice;
    }

    public void setCoefNonPromoPrice(Float coefNonPromoPrice) {
        this.coefNonPromoPrice = coefNonPromoPrice;
    }

    public Float getCoefACV() {
        return coefACV;
    }

    public void setCoefACV(Float coefACV) {
        this.coefACV = coefACV;
    }

    public Float getCoefSeasonality() {
        return coefSeasonality;
    }

    public void setCoefSeasonality(Float coefSeasonality) {
        this.coefSeasonality = coefSeasonality;
    }

    public Float getCoefDiscount() {
        return coefDiscount;
    }

    public void setCoefDiscount(Float coefDiscount) {
        this.coefDiscount = coefDiscount;
    }

    public Float getCoefPromoPrice() {
        return coefPromoPrice;
    }

    public void setCoefPromoPrice(Float coefPromoPrice) {
        this.coefPromoPrice = coefPromoPrice;
    }

    public Float getCoefType1() {
        return coefType1;
    }

    public void setCoefType1(Float coefType1) {
        this.coefType1 = coefType1;
    }

    public Float getCoefType2() {
        return coefType2;
    }

    public void setCoefType2(Float coefType2) {
        this.coefType2 = coefType2;
    }

    public Float getCoefType3() {
        return coefType3;
    }

    public void setCoefType3(Float coefType3) {
        this.coefType3 = coefType3;
    }

    public Float getCoefType4() {
        return coefType4;
    }

    public void setCoefType4(Float coefType4) {
        this.coefType4 = coefType4;
    }

    public Float getCoefType5() {
        return coefType5;
    }

    public void setCoefType5(Float coefType5) {
        this.coefType5 = coefType5;
    }

    public Float getCoefTactic1() {
        return coefTactic1;
    }

    public void setCoefTactic1(Float coefTactic1) {
        this.coefTactic1 = coefTactic1;
    }

    public Float getCoefTactic2() {
        return coefTactic2;
    }

    public void setCoefTactic2(Float coefTactic2) {
        this.coefTactic2 = coefTactic2;
    }

    public Float getCoefTactic3() {
        return coefTactic3;
    }

    public void setCoefTactic3(Float coefTactic3) {
        this.coefTactic3 = coefTactic3;
    }

    public Float getCoefTactic4() {
        return coefTactic4;
    }

    public void setCoefTactic4(Float coefTactic4) {
        this.coefTactic4 = coefTactic4;
    }

    public Float getCoefTactic5() {
        return coefTactic5;
    }

    public void setCoefTactic5(Float coefTactic5) {
        this.coefTactic5 = coefTactic5;
    }

    public Float getCoefTactic6() {
        return coefTactic6;
    }

    public void setCoefTactic6(Float coefTactic6) {
        this.coefTactic6 = coefTactic6;
    }

    public Float getCoefTactic7() {
        return coefTactic7;
    }

    public void setCoefTactic7(Float coefTactic7) {
        this.coefTactic7 = coefTactic7;
    }

    public Float getCoefTactic8() {
        return coefTactic8;
    }

    public void setCoefTactic8(Float coefTactic8) {
        this.coefTactic8 = coefTactic8;
    }

    public Float getCoefTactic9() {
        return coefTactic9;
    }

    public void setCoefTactic9(Float coefTactic9) {
        this.coefTactic9 = coefTactic9;
    }

    public Float getCoefTactic10() {
        return coefTactic10;
    }

    public void setCoefTactic10(Float coefTactic10) {
        this.coefTactic10 = coefTactic10;
    }

    public Float getCoefCompetitorNonPromo1() {
        return coefCompetitorNonPromo1;
    }

    public void setCoefCompetitorNonPromo1(Float coefCompetitorNonPromo1) {
        this.coefCompetitorNonPromo1 = coefCompetitorNonPromo1;
    }

    public Float getCoefCompetitorNonPromo2() {
        return coefCompetitorNonPromo2;
    }

    public void setCoefCompetitorNonPromo2(Float coefCompetitorNonPromo2) {
        this.coefCompetitorNonPromo2 = coefCompetitorNonPromo2;
    }

    public Float getCoefCompetitorNonPromo3() {
        return coefCompetitorNonPromo3;
    }

    public void setCoefCompetitorNonPromo3(Float coefCompetitorNonPromo3) {
        this.coefCompetitorNonPromo3 = coefCompetitorNonPromo3;
    }

    public Float getCoefCompetitorNonPromo4() {
        return coefCompetitorNonPromo4;
    }

    public void setCoefCompetitorNonPromo4(Float coefCompetitorNonPromo4) {
        this.coefCompetitorNonPromo4 = coefCompetitorNonPromo4;
    }

    public Float getCoefCompetitorNonPromo5() {
        return coefCompetitorNonPromo5;
    }

    public void setCoefCompetitorNonPromo5(Float coefCompetitorNonPromo5) {
        this.coefCompetitorNonPromo5 = coefCompetitorNonPromo5;
    }

    public Float getCoefCompetitorPromo1() {
        return coefCompetitorPromo1;
    }

    public void setCoefCompetitorPromo1(Float coefCompetitorPromo1) {
        this.coefCompetitorPromo1 = coefCompetitorPromo1;
    }

    public Float getCoefCompetitorPromo2() {
        return coefCompetitorPromo2;
    }

    public void setCoefCompetitorPromo2(Float coefCompetitorPromo2) {
        this.coefCompetitorPromo2 = coefCompetitorPromo2;
    }

    public Float getCoefCompetitorPromo3() {
        return coefCompetitorPromo3;
    }

    public void setCoefCompetitorPromo3(Float coefCompetitorPromo3) {
        this.coefCompetitorPromo3 = coefCompetitorPromo3;
    }

    public Float getCoefCompetitorPromo4() {
        return coefCompetitorPromo4;
    }

    public void setCoefCompetitorPromo4(Float coefCompetitorPromo4) {
        this.coefCompetitorPromo4 = coefCompetitorPromo4;
    }

    public Float getCoefCompetitorPromo5() {
        return coefCompetitorPromo5;
    }

    public void setCoefCompetitorPromo5(Float coefCompetitorPromo5) {
        this.coefCompetitorPromo5 = coefCompetitorPromo5;
    }

    public Float getCoefCompetitorAverageNonPromo() {
        return coefCompetitorAverageNonPromo;
    }

    public void setCoefCompetitorAverageNonPromo(Float coefCompetitorAverageNonPromo) {
        this.coefCompetitorAverageNonPromo = coefCompetitorAverageNonPromo;
    }

    public Float getCoefCompetitorAveragePromo() {
        return coefCompetitorAveragePromo;
    }

    public void setCoefCompetitorAveragePromo(Float coefCompetitorAveragePromo) {
        this.coefCompetitorAveragePromo = coefCompetitorAveragePromo;
    }

    public String getSubstituteLevel() {
        return substituteLevel;
    }

    public void setSubstituteLevel(String substituteLevel) {
        this.substituteLevel = substituteLevel;
    }

    public String getMapeEst() {
        return mapeEst;
    }

    public void setMapeEst(String mapeEst) {
        this.mapeEst = mapeEst;
    }
}

package us.deloitteinnovation.polaris.eventeval.model;

import java.util.Date;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class EventEvalPromotionVO {

    private int promotionId;

    private int promotionCampaignCode;

    private int productId;

    private int customerId;

    private String promotionType;

    private String promotionTactic;

    private int promotionPlannedUnits;

    private float actualPromotionPrice;

    private int promotionBaselineUnits;

    private int promotionActualUnits;

    private float promotionActualCost;

    private float promotionPlannedCost;

    private String promotionSpendType;

    private Date simulatedStartDate;

    private Date simulatedEndDate;

    private String simulatedPromotionTactic;

    private int simulatedPromotionUnits;

    private float simulatedPromotionPrice;

    private float simulatedVolumeLiftCoefficient;

    private float coefficientValue;

    private float depth;

    private float dayDifference;

    private boolean isSimulated;

    private float productListPrice;

    private float tacticCoupon;

    private float tacticTV;

    private float tacticDisplay;

    private float tacticOne;

    private float tacticTwo;

    private float tacticThree;

    private float tacticFour;

    private float tacticFive;

    private float tacticSix;

    private float tacticSeven;

    private float tacticEight;

    private float simulatedRevenue;

    private float simulatedLift;

    private float simulatedPromotionIncrementalUnits;

    private float simulatedROI;

    private float simulatedMargin;

    private int simulatedDepth;

    private float simulatedPromotionDiscountDepth;

    private String tactic;

    private float actualRevenue;

    private float actualMargin;

    private float actualROI;

    private float actualLift;

    private float actualDepth;

    private Date actualStartDate;

    private Date actualEndDate;



    /**
     * @return the promotionCampaignCode
     */
    public int getPromotionCampaignCode() {
        return promotionCampaignCode;
    }

    /**
     * @param promotionCampaignCode the promotionCampaignCode to set
     */
    public void setPromotionCampaignCode(int promotionCampaignCode) {
        this.promotionCampaignCode = promotionCampaignCode;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the promotionType
     */
    public String getPromotionType() {
        return promotionType;
    }

    /**
     * @param promotionType the promotionType to set
     */
    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    /**
     * @return the promotionTactic
     */
    public String getPromotionTactic() {
        return promotionTactic;
    }

    /**
     * @param promotionTactic the promotionTactic to set
     */
    public void setPromotionTactic(String promotionTactic) {
        this.promotionTactic = promotionTactic;
    }

    /**
     * @return the promotionPlannedUnits
     */
    public int getPromotionPlannedUnits() {
        return promotionPlannedUnits;
    }

    /**
     * @param promotionPlannedUnits the promotionPlannedUnits to set
     */
    public void setPromotionPlannedUnits(int promotionPlannedUnits) {
        this.promotionPlannedUnits = promotionPlannedUnits;
    }

    /**
     * @return the promotionBaselineUnits
     */
    public int getPromotionBaselineUnits() {
        return promotionBaselineUnits;
    }

    /**
     * @param promotionBaselineUnits the promotionBaselineUnits to set
     */
    public void setPromotionBaselineUnits(int promotionBaselineUnits) {
        this.promotionBaselineUnits = promotionBaselineUnits;
    }

    /**
     * @return the promotionActualUnits
     */
    public int getPromotionActualUnits() {
        return promotionActualUnits;
    }

    /**
     * @param promotionActualUnits the promotionActualUnits to set
     */
    public void setPromotionActualUnits(int promotionActualUnits) {
        this.promotionActualUnits = promotionActualUnits;
    }

    /**
     * @return the promotionActualCost
     */
    public float getPromotionActualCost() {
        return promotionActualCost;
    }

    /**
     * @param promotionActualCost the promotionActualCost to set
     */
    public void setPromotionActualCost(float promotionActualCost) {
        this.promotionActualCost = promotionActualCost;
    }

    /**
     * @return the promotionPlannedCost
     */
    public float getPromotionPlannedCost() {
        return promotionPlannedCost;
    }

    /**
     * @param promotionPlannedCost the promotionPlannedCost to set
     */
    public void setPromotionPlannedCost(float promotionPlannedCost) {
        this.promotionPlannedCost = promotionPlannedCost;
    }

    /**
     * @return the promotionSpendType
     */
    public String getPromotionSpendType() {
        return promotionSpendType;
    }

    /**
     * @param promotionSpendType the promotionSpendType to set
     */
    public void setPromotionSpendType(String promotionSpendType) {
        this.promotionSpendType = promotionSpendType;
    }

    /**
     * @return the simulatedPromotionTactic
     */
    public String getSimulatedPromotionTactic() {
        return simulatedPromotionTactic;
    }

    /**
     * @param simulatedPromotionTactic the simulatedPromotionTactic to set
     */
    public void setSimulatedPromotionTactic(String simulatedPromotionTactic) {
        this.simulatedPromotionTactic = simulatedPromotionTactic;
    }

    /**
     * @return the simulatedPromotionUnits
     */
    public int getSimulatedPromotionUnits() {
        return simulatedPromotionUnits;
    }

    /**
     * @param simulatedPromotionUnits the simulatedPromotionUnits to set
     */
    public void setSimulatedPromotionUnits(int simulatedPromotionUnits) {
        this.simulatedPromotionUnits = simulatedPromotionUnits;
    }

    /**
     * @return the simulatedPromotionDiscountDepth
     */
    public float getSimulatedPromotionDiscountDepth() {
        return simulatedPromotionDiscountDepth;
    }

    /**
     * @param simulatedPromotionDiscountDepth the simulatedPromotionDiscountDepth to set
     */
    public void setSimulatedPromotionDiscountDepth(
            float simulatedPromotionDiscountDepth) {
        this.simulatedPromotionDiscountDepth = simulatedPromotionDiscountDepth;
    }

    /**
     * @return the simulatedVolumeLiftCoefficient
     */
    public float getSimulatedVolumeLiftCoefficient() {
        return simulatedVolumeLiftCoefficient;
    }

    /**
     * @param simulatedVolumeLiftCoefficient the simulatedVolumeLiftCoefficient to set
     */
    public void setSimulatedVolumeLiftCoefficient(
            float simulatedVolumeLiftCoefficient) {
        this.simulatedVolumeLiftCoefficient = simulatedVolumeLiftCoefficient;
    }

    /**
     * @return the promotionId
     */
    public int getPromotionId() {
        return promotionId;
    }

    /**
     * @param promotionId the promotionId to set
     */
    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the coefficientValue
     */
    public float getCoefficientValue() {
        return coefficientValue;
    }

    /**
     * @param coefficientValue the coefficientValue to set
     */
    public void setCoefficientValue(float coefficientValue) {
        this.coefficientValue = coefficientValue;
    }

    /**
     * @return the tacticCoupon
     */
    public float getTacticCoupon() {
        return tacticCoupon;
    }

    /**
     * @param tacticCoupon the tacticCoupon to set
     */
    public void setTacticCoupon(float tacticCoupon) {
        this.tacticCoupon = tacticCoupon;
    }

    /**
     * @return the tacticTV
     */
    public float getTacticTV() {
        return tacticTV;
    }

    /**
     * @param tacticTV the tacticTV to set
     */
    public void setTacticTV(float tacticTV) {
        this.tacticTV = tacticTV;
    }

    /**
     * @return the tacticDisplay
     */
    public float getTacticDisplay() {
        return tacticDisplay;
    }

    /**
     * @param tacticDisplay the tacticDisplay to set
     */
    public void setTacticDisplay(float tacticDisplay) {
        this.tacticDisplay = tacticDisplay;
    }

    /**
     * @return the depth
     */
    public float getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(float depth) {
        this.depth = depth;
    }

    /**
     * @return the dayDifference
     */
    public float getDayDifference() {
        return dayDifference;
    }

    /**
     * @param dayDifference the dayDifference to set
     */
    public void setDayDifference(float dayDifference) {
        this.dayDifference = dayDifference;
    }

    /**
     * @return the productListPrice
     */
    public float getProductListPrice() {
        return productListPrice;
    }

    /**
     * @param productListPrice the productListPrice to set
     */
    public void setProductListPrice(float productListPrice) {
        this.productListPrice = productListPrice;
    }

    /**
     * @return the isSimulated
     */
    public boolean isSimulated() {
        return isSimulated;
    }

    /**
     * @param isSimulated the isSimulated to set
     */
    public void setSimulated(boolean isSimulated) {
        this.isSimulated = isSimulated;
    }

    /**
     * @return the simulatedPromotionPrice
     */
    public float getSimulatedPromotionPrice() {
        return simulatedPromotionPrice;
    }

    /**
     * @param simulatedPromotionPrice the simulatedPromotionPrice to set
     */
    public void setSimulatedPromotionPrice(float simulatedPromotionPrice) {
        this.simulatedPromotionPrice = simulatedPromotionPrice;
    }

    /**
     * @return the tacticOne
     */
    public float getTacticOne() {
        return tacticOne;
    }

    /**
     * @param tacticOne the tacticOne to set
     */
    public void setTacticOne(float tacticOne) {
        this.tacticOne = tacticOne;
    }

    /**
     * @return the tacticTwo
     */
    public float getTacticTwo() {
        return tacticTwo;
    }

    /**
     * @param tacticTwo the tacticTwo to set
     */
    public void setTacticTwo(float tacticTwo) {
        this.tacticTwo = tacticTwo;
    }

    /**
     * @return the tacticThree
     */
    public float getTacticThree() {
        return tacticThree;
    }

    /**
     * @param tacticThree the tacticThree to set
     */
    public void setTacticThree(float tacticThree) {
        this.tacticThree = tacticThree;
    }

    /**
     * @return the tacticFour
     */
    public float getTacticFour() {
        return tacticFour;
    }

    /**
     * @param tacticFour the tacticFour to set
     */
    public void setTacticFour(float tacticFour) {
        this.tacticFour = tacticFour;
    }

    /**
     * @return the tacticFive
     */
    public float getTacticFive() {
        return tacticFive;
    }

    /**
     * @param tacticFive the tacticFive to set
     */
    public void setTacticFive(float tacticFive) {
        this.tacticFive = tacticFive;
    }

    /**
     * @return the tacticSix
     */
    public float getTacticSix() {
        return tacticSix;
    }

    /**
     * @param tacticSix the tacticSix to set
     */
    public void setTacticSix(float tacticSix) {
        this.tacticSix = tacticSix;
    }

    /**
     * @return the tacticSeven
     */
    public float getTacticSeven() {
        return tacticSeven;
    }

    /**
     * @param tacticSeven the tacticSeven to set
     */
    public void setTacticSeven(float tacticSeven) {
        this.tacticSeven = tacticSeven;
    }

    /**
     * @return the tacticEight
     */
    public float getTacticEight() {
        return tacticEight;
    }

    /**
     * @param tacticEight the tacticEight to set
     */
    public void setTacticEight(float tacticEight) {
        this.tacticEight = tacticEight;
    }

    /**
     * @return the simulatedStartDate
     */
    public Date getSimulatedStartDate() {
        return simulatedStartDate != null ? (Date) simulatedStartDate.clone() : null;
    }

    /**
     * @param simulatedStartDate the simulatedStartDate to set
     */
    public void setSimulatedStartDate(Date simulatedStartDate) {
        this.simulatedStartDate = simulatedStartDate != null ? new Date(simulatedStartDate.getTime()) : null;
    }

    /**
     * @return the simulatedEndDate
     */
    public Date getSimulatedEndDate() {
    	return simulatedEndDate != null ? (Date) simulatedEndDate.clone() : null;
    }

    /**
     * @param simulatedEndDate the simulatedEndDate to set
     */
    public void setSimulatedEndDate(Date simulatedEndDate) {
    	this.simulatedEndDate = simulatedEndDate != null ? new Date(simulatedEndDate.getTime()) : null;
    }

    /**
     * @return the simulatedRevenue
     */
    public float getSimulatedRevenue() {
        return simulatedRevenue;
    }

    /**
     * @param simulatedRevenue the simulatedRevenue to set
     */
    public void setSimulatedRevenue(float simulatedRevenue) {
        this.simulatedRevenue = simulatedRevenue;
    }

    /**
     * @return the simulatedLift
     */
    public float getSimulatedLift() {
        return simulatedLift;
    }

    /**
     * @param simulatedLift the simulatedLift to set
     */
    public void setSimulatedLift(float simulatedLift) {
        this.simulatedLift = simulatedLift;
    }

    /**
     * @return the simulatedPromotionIncrementalUnits
     */
    public float getSimulatedPromotionIncrementalUnits() {
        return simulatedPromotionIncrementalUnits;
    }

    /**
     * @param simulatedPromotionIncrementalUnits the simulatedPromotionIncrementalUnits to set
     */
    public void setSimulatedPromotionIncrementalUnits(
            float simulatedPromotionIncrementalUnits) {
        this.simulatedPromotionIncrementalUnits = simulatedPromotionIncrementalUnits;
    }

    /**
     * @return the simulatedROI
     */
    public float getSimulatedROI() {
        return simulatedROI;
    }

    /**
     * @param simulatedROI the simulatedROI to set
     */
    public void setSimulatedROI(float simulatedROI) {
        this.simulatedROI = simulatedROI;
    }

    /**
     * @return the simulatedMargin
     */
    public float getSimulatedMargin() {
        return simulatedMargin;
    }

    /**
     * @param simulatedMargin the simulatedMargin to set
     */
    public void setSimulatedMargin(float simulatedMargin) {
        this.simulatedMargin = simulatedMargin;
    }

    /**
     * @return the simulatedDepth
     */
    public int getSimulatedDepth() {
        return simulatedDepth;
    }

    /**
     * @param simulatedDepth the simulatedDepth to set
     */
    public void setSimulatedDepth(int simulatedDepth) {
        this.simulatedDepth = simulatedDepth;
    }

    /**
     * @return the tactic
     */
    public String getTactic() {
        return tactic;
    }

    /**
     * @param tactic the tactic to set
     */
    public void setTactic(String tactic) {
        this.tactic = tactic;
    }

    /**
     * @return the actualPromotionPrice
     */
    public float getActualPromotionPrice() {
        return actualPromotionPrice;
    }

    /**
     * @param actualPromotionPrice the actualPromotionPrice to set
     */
    public void setActualPromotionPrice(float actualPromotionPrice) {
        this.actualPromotionPrice = actualPromotionPrice;
    }

    /**
     * @return the actualRevenue
     */
    public float getActualRevenue() {
        return actualRevenue;
    }

    /**
     * @param actualRevenue the actualRevenue to set
     */
    public void setActualRevenue(float actualRevenue) {
        this.actualRevenue = actualRevenue;
    }

    /**
     * @return the actualMargin
     */
    public float getActualMargin() {
        return actualMargin;
    }

    /**
     * @param actualMargin the actualMargin to set
     */
    public void setActualMargin(float actualMargin) {
        this.actualMargin = actualMargin;
    }

    /**
     * @return the actualROI
     */
    public float getActualROI() {
        return actualROI;
    }

    /**
     * @param actualROI the actualROI to set
     */
    public void setActualROI(float actualROI) {
        this.actualROI = actualROI;
    }

    /**
     * @return the actualLift
     */
    public float getActualLift() {
        return actualLift;
    }

    /**
     * @param actualLift the actualLift to set
     */
    public void setActualLift(float actualLift) {
        this.actualLift = actualLift;
    }

    /**
     * @return the actualDepth
     */
    public float getActualDepth() {
        return actualDepth;
    }

    /**
     * @param actualDepth the actualDepth to set
     */
    public void setActualDepth(float actualDepth) {
        this.actualDepth = actualDepth;
    }

    /**
     * @return the actualStartDate
     */
    public Date getActualStartDate() {
        return actualStartDate != null ? (Date) actualStartDate.clone() : null;
    }

    /**
     * @param actualStartDate the actualStartDate to set
     */
    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate != null ? new Date(actualStartDate.getTime()) : null;
    }

    /**
     * @return the actualEndDate
     */
    public Date getActualEndDate() {
    	return actualEndDate != null ? (Date) actualEndDate.clone() : null;
    }

    /**
     * @param actualEndDate the actualEndDate to set
     */
    public void setActualEndDate(Date actualEndDate) {
    	this.actualEndDate = actualEndDate != null ? new Date(actualEndDate.getTime()) : null;
    }


}

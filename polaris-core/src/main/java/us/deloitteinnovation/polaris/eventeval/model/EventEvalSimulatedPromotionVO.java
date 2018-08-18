package us.deloitteinnovation.polaris.eventeval.model;

import java.util.Date;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class EventEvalSimulatedPromotionVO {

    private int productId;
    private int customerId;
    private int promotionId;
    private int tacticFlag;
    private String tactic;
    private int depthFlag;
    private int depthValue;
    private int daysFlag;
    private int daysValue;
    private Date simulatedStartDate;
    private Date simulatedEndDate;

    public int getTacticFlag() {
        return tacticFlag;
    }
    public void setTacticFlag(int tacticFlag) {
        this.tacticFlag = tacticFlag;
    }
    public String getTactic() {
        return tactic;
    }
    public void setTactic(String tactic) {
        this.tactic = tactic;
    }
    public int getDepthFlag() {
        return depthFlag;
    }
    public void setDepthFlag(int depthFlag) {
        this.depthFlag = depthFlag;
    }
    public int getDepthValue() {
        return depthValue;
    }
    public void setDepthValue(int depthValue) {
        this.depthValue = depthValue;
    }
    public int getDaysFlag() {
        return daysFlag;
    }
    public void setDaysFlag(int daysFlag) {
        this.daysFlag = daysFlag;
    }
    public int getDaysValue() {
        return daysValue;
    }
    public void setDaysValue(int daysValue) {
        this.daysValue = daysValue;
    }
    public Date getSimulatedStartDate() {
    	return simulatedStartDate != null ? (Date) simulatedStartDate.clone() : null;
    }
    public void setSimulatedStartDate(Date simulatedStartDate) {
    	this.simulatedStartDate = simulatedStartDate != null ? new Date(simulatedStartDate.getTime()) : null;
    }
    public Date getSimulatedEndDate() {
    	return simulatedEndDate != null ? (Date) simulatedEndDate.clone() : null;
    }
    public void setSimulatedEndDate(Date simulatedEndDate) {
        this.simulatedEndDate = simulatedEndDate != null ? new Date(simulatedEndDate.getTime()) : null;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getPromotionId() {
        return promotionId;
    }
    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

}

package us.deloitteinnovation.polaris.evalcalculation.model;

/**
 * Created by rbentaarit on 9/19/2016.
 */

public class PromotionDetail {

    private Integer promotionId;
    private String promotionName;
    private Integer customerId;
    private String customerName;
    private Integer productId;
    private String productName;
    private String promotionType;
    private String promotionTactic;
    private String date;
    private Double promotionPrice;
    private Double nonPromotionPrice;
    private Double originalVolume;


    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionTactic() {
        return promotionTactic;
    }

    public void setPromotionTactic(String promotionTactic) {
        this.promotionTactic = promotionTactic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Double getNonPromotionPrice() {
        return nonPromotionPrice;
    }

    public void setNonPromotionPrice(Double nonPromotionPrice) {
        this.nonPromotionPrice = nonPromotionPrice;
    }

    public Double getOriginalVolume() {
        return originalVolume;
    }

    public void setOriginalVolume(Double originalVolume) {
        this.originalVolume = originalVolume;
    }
}

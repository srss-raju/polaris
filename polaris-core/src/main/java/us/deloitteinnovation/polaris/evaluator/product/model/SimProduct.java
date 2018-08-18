package us.deloitteinnovation.polaris.evaluator.product.model;

import us.deloitteinnovation.polaris.evaluator.calculation.model.Metric;
import javax.validation.constraints.NotNull;

/**
 * Created by rbentaarit on 6/11/2017.
 */
public class SimProduct {

    private Integer id;
    @NotNull
    private Integer productId;
    private String productName;
    @NotNull
    private Float regularPrice;
    @NotNull
    private Float promoPrice;
    private String currency;
    @NotNull
    private Integer eventId;
    private String skuId;
    private Float spend;

    private Metric metric;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Float regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Float getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Float promoPrice) {
        this.promoPrice = promoPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Float getSpend() {
        return spend;
    }

    public void setSpend(Float spend) {
        this.spend = spend;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}

package us.deloitteinnovation.polaris.evalcalculation.utils.evalrequest;


import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * Created by rbentaarit on 8/30/2016.
 */
public class Promotion {

    @NotNull
    Integer productId;

    @NotEmpty
    String type;

    @NotEmpty
    String tactic;

    @Pattern(regexp="^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])", message = "Invalid date format")
    @NotEmpty
    String date;

    @DecimalMin("0.01")
    @NotNull
    Float promotionPrice;

    @DecimalMin("0.01")
    @NotNull
    Float nonPromotionPrice;

    private LocalDate promotionDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }

    public String getDate() {
        return date;
    }

    public LocalDate getPromotionDate() {
        return promotionDate;
    }

    public void setDate(String date) {
        this.date = date;

        this.promotionDate = new LocalDate(date);
    }

    public Float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Float getNonPromotionPrice() {
        return nonPromotionPrice;
    }

    public void setNonPromotionPrice(Float nonPromotionPrice) {
        this.nonPromotionPrice = nonPromotionPrice;
    }
}

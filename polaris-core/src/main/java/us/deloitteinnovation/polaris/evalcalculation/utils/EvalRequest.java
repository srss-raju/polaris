package us.deloitteinnovation.polaris.evalcalculation.utils;

import us.deloitteinnovation.polaris.evalcalculation.utils.evalrequest.Promotion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rbentaarit on 8/30/2016.
 */
public class EvalRequest {


    @NotNull
    Integer customerId;


    @Valid
    Promotion originalPromotion;

    @Valid
    Promotion targetPromotion;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Promotion getOriginalPromotion() {
        return originalPromotion;
    }

    public void setOriginalPromotion(Promotion originalPromotion) {
        this.originalPromotion = originalPromotion;
    }

    public Promotion getTargetPromotion() {
        return targetPromotion;
    }

    public void setTargetPromotion(Promotion targetPromotion) {
        this.targetPromotion = targetPromotion;
    }
}

package us.deloitteinnovation.polaris.evalcalculation.service;

import us.deloitteinnovation.polaris.evalcalculation.model.PromoEffectivenessWhen;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionDetail;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
public interface IPromotionDetailService {

    PromotionDetail getPromotionDetailByPromotionId(Integer promotionId);
    List<PromoEffectivenessWhen> getPromoEffectivenessWhen(Integer offSet, Integer pageSize);
}

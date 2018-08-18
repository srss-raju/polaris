package us.deloitteinnovation.polaris.evalcalculation.dao;

import us.deloitteinnovation.polaris.evalcalculation.model.PromoEffectivenessWhen;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionDetail;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
public interface IPromotionDetailDAO {
    PromotionDetail getPromotionDetailByPromotionId(int promotionId);
    List<PromoEffectivenessWhen> getPromoEffectivenessWhen(Integer offSet, Integer pageSize);
}

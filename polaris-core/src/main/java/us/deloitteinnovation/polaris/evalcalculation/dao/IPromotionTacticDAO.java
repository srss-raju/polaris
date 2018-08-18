package us.deloitteinnovation.polaris.evalcalculation.dao;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@FunctionalInterface
public interface IPromotionTacticDAO {
    List<PromotionTactic> getAllPromotionTactic();
}

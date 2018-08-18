package us.deloitteinnovation.polaris.evalcalculation.service;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@FunctionalInterface
public interface IPromotionTacticService {

     List<PromotionTactic> getAllTactic();
}

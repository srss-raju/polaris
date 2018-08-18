package us.deloitteinnovation.polaris.evalcalculation.service;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@FunctionalInterface
public interface IPromotionTypeService {

    List<PromotionType> getAllPromotionType();
}

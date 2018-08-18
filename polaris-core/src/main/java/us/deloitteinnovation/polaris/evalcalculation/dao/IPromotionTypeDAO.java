package us.deloitteinnovation.polaris.evalcalculation.dao;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@FunctionalInterface
public interface IPromotionTypeDAO {
    List<PromotionType> getAllPromotionType();
}

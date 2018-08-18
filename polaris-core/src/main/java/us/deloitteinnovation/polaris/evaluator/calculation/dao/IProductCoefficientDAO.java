package us.deloitteinnovation.polaris.evaluator.calculation.dao;

import us.deloitteinnovation.polaris.evaluator.calculation.model.ProductCoefficient;

/**
 * Created by rbentaarit on 7/12/2017.
 */
@FunctionalInterface
public interface IProductCoefficientDAO {
    ProductCoefficient getProductCoefficient(Integer productId, Integer customerId);
}

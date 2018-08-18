package us.deloitteinnovation.polaris.evaluator.product.service;

import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;

import java.util.List;

/**
 * Created by rbentaarit on 6/11/2017.
 */
public interface ISimProductService {

    int[] insertSimProducts(List<SimProduct> simProducts);
    List<SimProduct> getSimProductsByEventId(Integer eventId);
    SimProduct insertSimProduct(SimProduct product);
    SimProduct getSimProductById(Integer simProductId);
    Boolean deleteSimProduct(Integer simProductId);
    int[] updateSimProducts(List<SimProduct> simProducts);
}

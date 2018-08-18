package us.deloitteinnovation.polaris.evaluator.product.dao;

import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;

import java.util.List;

/**
 * Created by rbentaarit on 6/11/2017.
 */
public interface ISimProductDAO {
    int[] insertProducts(List<SimProduct> products);
    List<SimProduct> getSimProductsByEventId(Integer eventId);
    Integer insertSimProduct(SimProduct product);
    SimProduct getSimProductById(Integer simProductId);
    int[] updateSimProduct(List<SimProduct> simProducts);
    int deleteSimProduct(Integer simProductId);
}

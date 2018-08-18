package us.deloitteinnovation.polaris.evaluator.product.dao;

import us.deloitteinnovation.polaris.evaluator.product.model.Product;
import us.deloitteinnovation.polaris.evaluator.product.model.SearchResultResponse;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */

public interface IProductDAO {
    List<Product> getAllProduct();
    List<Product> getProductsByClientProductID(String clientProductId);
    SearchResultResponse findProductDetailsBySearchText(String searchText, Integer offSet, Integer size);
    Integer findProductDetailsSizeBySearchText(String searchText);
}

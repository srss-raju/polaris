package us.deloitteinnovation.polaris.evaluator.product.model;

import java.util.List;

/**
 * Created by araju on 6/20/2017.
 */
public class SearchResultResponse {

    private Integer count;
    private List<Product> products;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

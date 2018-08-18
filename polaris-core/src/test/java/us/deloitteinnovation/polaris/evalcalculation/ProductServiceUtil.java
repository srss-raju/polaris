package us.deloitteinnovation.polaris.evalcalculation;

import us.deloitteinnovation.polaris.evaluator.product.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class ProductServiceUtil {

    public static Product getProduct(Integer productId, String productName) {
        Product product = new Product();
        if (null != productId) {
            product.setProductId(1);
        }
         product.setProductName("Name");
        return  product;
    }

    public static List<Product> getProductList(int productCount) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productCount; i++) {
            products.add(ProductServiceUtil.getProduct(1,"title"));
        }
        return products;
    }
}

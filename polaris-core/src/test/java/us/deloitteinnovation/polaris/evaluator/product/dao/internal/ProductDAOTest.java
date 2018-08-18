package us.deloitteinnovation.polaris.evaluator.product.dao.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evaluator.product.model.Product;
import us.deloitteinnovation.polaris.evaluator.product.model.SearchResultResponse;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hkaja on 09-06-2017.
 */
public class ProductDAOTest extends AbstractTest {

    private ProductDAOImpl productDAO;

    @Before
    public void setUp() throws Exception {
        productDAO = new ProductDAOImpl();
        this.productDAO.setDatasource(dataSource);
    }

    @Test
    public void getAllProductTest() {
        List<Product> products = productDAO.getAllProduct();
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }

    @Test
    public void getProductsByClientProductID() throws Exception {
        List<Product> test = productDAO.getProductsByClientProductID("test");
        Assert.assertTrue(test.size() >= 0);

    }

    @Test
    public void findProductDetailsBySearchText() throws Exception {
        List<Product> products = productDAO.getAllProduct();
        Random rand = new Random();
        int randomNum = rand.nextInt(products.size());
        Product product = products.get(randomNum);

        SearchResultResponse sRR = productDAO.findProductDetailsBySearchText("A0700X3VX087026S1M", 1, 10);
        assertNotNull(sRR.getProducts());
        assertTrue(sRR.getProducts().size() >= 0);
    }

    @Test
    public void findProductDetailsBySearchTextWithWrongOffSet() throws Exception {
        List<Product> products = productDAO.getAllProduct();
        Random rand = new Random();
        int randomNum = rand.nextInt(products.size());
        Product product = products.get(randomNum);

        SearchResultResponse sRR = productDAO.findProductDetailsBySearchText(product.getProductName(), 0, 10);
        assertNotNull(sRR);
        assertTrue(sRR.getProducts().size() >= 0);
    }

    @Test
    public void findProductDetailsSizeBySearchTextPositiveTest() throws Exception {
        List<Product> products = productDAO.getAllProduct();
        Random rand = new Random();
        int randomNum = rand.nextInt(products.size());
        Product product = products.get(randomNum);
        Integer count = productDAO.findProductDetailsSizeBySearchText(product.getProductName());
        assertNotNull(count);
        assertTrue(count >= 0);
    }

    @Test
    public void findProductDetailsSizeBySearchTextNegativeTest() throws Exception {
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        Integer count = productDAO.findProductDetailsSizeBySearchText("XYZPRODUCT"+randomNum);
        assertNotNull(count);
        assertEquals((int)count, 0);
    }

}
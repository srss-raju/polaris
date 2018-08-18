package us.deloitteinnovation.polaris.evaluator.product.service.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.ProductServiceUtil;
import us.deloitteinnovation.polaris.evaluator.product.dao.IProductDAO;
import us.deloitteinnovation.polaris.evaluator.product.model.Product;
import us.deloitteinnovation.polaris.evaluator.product.model.SearchResultResponse;
import us.deloitteinnovation.polaris.evaluator.product.service.IProductService;

import java.util.List;
import java.util.Random;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class ProductServiceTest extends AbstractTest {

    @Mock
    private IProductDAO productDAO;

    private IProductService productService;

    @Before
    public void setup() {
        productService = new ProductServiceImpl(productDAO);
    }

    @Test
    public void getProductListtest() {
        List<Product> products = ProductServiceUtil.getProductList(2);
        Mockito.when(productDAO.getAllProduct()).thenReturn(products);
        Assert.assertEquals(products, productService.getAllProduct());

    }

    @Test
    public void productSearchTest() {
        List<Product> products = ProductServiceUtil.getProductList(2);
        Random rand = new Random();
        int randomNum = rand.nextInt(products.size());
        Product product = products.get(randomNum);
        SearchResultResponse sRR = new SearchResultResponse();
        sRR.setCount(products.size());
        sRR.setProducts(products);
        Mockito.when(productDAO.findProductDetailsBySearchText(Matchers.anyString(), Matchers.anyInt(), Matchers.anyInt())).thenReturn(sRR);
        Assert.assertTrue(productService.findProductDetailsBySearchText(product.getProductName(), 1, 10).getProducts().size()> 0);
    }

    @Test
    public void getProductsByClientProductIDTest() {
        List<Product> products = ProductServiceUtil.getProductList(2);
        Random rand = new Random();
        int randomNum = rand.nextInt(products.size());
        Product product = products.get(randomNum);
        Mockito.when(productDAO.getProductsByClientProductID(Matchers.anyString())).thenReturn(products);
        Assert.assertTrue(productService.getProductsByClientProductID(product.getProductName()).size()> 0);
    }


}

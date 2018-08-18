package us.deloitteinnovation.polaris.evaluator.product.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evaluator.product.dao.IProductDAO;
import us.deloitteinnovation.polaris.evaluator.product.model.Product;
import us.deloitteinnovation.polaris.evaluator.product.model.SearchResultResponse;
import us.deloitteinnovation.polaris.evaluator.product.service.IProductService;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Service
public class ProductServiceImpl implements IProductService {
    private final IProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(IProductDAO IProductDAO) {
        this.productDAO = IProductDAO;
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public List<Product> getProductsByClientProductID(String clientProductId) {
        return productDAO.getProductsByClientProductID(clientProductId);
    }

    @Override
    public SearchResultResponse findProductDetailsBySearchText(String searchText, Integer offSet, Integer size) {
        return productDAO.findProductDetailsBySearchText(searchText, offSet, size);
    }
}

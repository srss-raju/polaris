package us.deloitteinnovation.polaris.evaluator.calculation.dao.internal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.evaluator.calculation.dao.IProductCoefficientDAO;
import us.deloitteinnovation.polaris.evaluator.calculation.model.ProductCoefficient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rbentaarit on 7/12/2017.
 */
@Repository
public class ProductCoefficientDAOImpl extends AbstractDAO implements IProductCoefficientDAO {

   private static final String GET_TOP_PRODUCT_COEFFICIENT = "SELECT * FROM [dbo].[mdl_Demand_Model] WHERE [Product_ID] = :productId";

    @Override
    public ProductCoefficient getProductCoefficient(Integer productId, Integer customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("customerId", customerId);
        return  getNamedParameterJdbcTemplate().queryForObject(GET_TOP_PRODUCT_COEFFICIENT, params, new BeanPropertyRowMapper<>(ProductCoefficient.class));
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("mdl_Demand_Model");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ID");
    }
}

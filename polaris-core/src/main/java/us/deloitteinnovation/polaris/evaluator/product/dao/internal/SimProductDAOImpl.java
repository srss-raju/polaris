package us.deloitteinnovation.polaris.evaluator.product.dao.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.evaluator.product.dao.ISimProductDAO;
import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;

import java.util.*;

/**
 * Created by rbentaarit on 6/11/2017.
 */

@Repository
public class SimProductDAOImpl extends AbstractDAO implements ISimProductDAO {

    private static final String INSERT_PRODUCTS = "INSERT INTO [dbo].[app_Sim_Product] ([Product_ID] ,[Regular_Price] ,[Promo_Price], [Event_ID], [Spend]) " +
            " VALUES (:productId, :regularPrice, :promoPrice, :eventId, :spend)";
    private static final String GET_PRODUCTS_BY_EVENT = "SELECT [app_Sim_Product].[ID] as ID, [Product_ID], [Regular_Price], [Promo_Price], [Event_ID], [Product_Name], [Product_List_Price_UoM] as currency , [Client_Product_ID] as skuId, [Spend]" +
            " FROM [dbo].[app_Sim_Product] INNER JOIN [tbl_Product_Master] ON [tbl_Product_Master].[ID] = [app_Sim_Product].[Product_ID] WHERE [EVENT_ID] = :eventId";
    private static final String INSERT_PRODUCT = "INSERT INTO [dbo].[app_Sim_Product] ([Product_ID] ,[Regular_Price] ,[Promo_Price], [Event_ID], [Spend]) " +
            "output Inserted.Id VALUES (:productId, :regularPrice, :promoPrice, :eventId, :spend)";
    private static final String GET_PRODUCTS_BY_ID = "SELECT [app_Sim_Product].[ID] as ID, [Product_ID], [Regular_Price], [Promo_Price], [Event_ID], [Product_Name], [Product_List_Price_UoM] as currency , [Client_Product_ID] as skuId, [Spend]" +
            " FROM [dbo].[app_Sim_Product] INNER JOIN [tbl_Product_Master] ON [tbl_Product_Master].[ID] = [app_Sim_Product].[Product_ID] WHERE [app_Sim_Product].[ID] = :simProductId";
    private static final String UPDATE_PRODUCTS = "UPDATE [dbo].[app_Sim_Product] " +
            " SET [Regular_Price]=:regularPrice, [Promo_Price]=:promoPrice, [Spend]=:spend " +
            " WHERE [Product_ID]=:productId";
    private static final String DELETE_PRODUCT = "DELETE [dbo].[app_Sim_Product] " +
            " FROM [dbo].[app_Sim_Product] " +
            " INNER JOIN  [dbo].[app_Event] on [dbo].[app_Sim_Product].[Event_ID] = [dbo].[app_Event].[ID] " +
            " Where [dbo].[app_Sim_Product].[ID]=:simProductId ";

    @Autowired
    private AuditorAware auditorAware;

    @Override
    public int[] insertProducts(List<SimProduct> products){
        return getNamedParameterJdbcTemplate().batchUpdate(INSERT_PRODUCTS, SqlParameterSourceUtils.createBatch(products.toArray()));
    }

    @Override
    public Integer insertSimProduct(SimProduct product){
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_PRODUCT,  new ObjectMapper().convertValue(product, Map.class), Integer.class);
    }

    @Override
    public SimProduct getSimProductById(Integer simProductId){
        return getNamedParameterJdbcTemplate().queryForObject(GET_PRODUCTS_BY_ID, Collections.singletonMap("simProductId", simProductId), new BeanPropertyRowMapper<>(SimProduct.class));
    }

    @Override
    public int[] updateSimProduct(List<SimProduct> simProducts) {
        return getNamedParameterJdbcTemplate().batchUpdate(UPDATE_PRODUCTS, SqlParameterSourceUtils.createBatch(simProducts.toArray()));
    }

    @Override
    public int deleteSimProduct(Integer simProductId) {
        Map<String, Object> params =  new HashMap<>();
        params.put("simProductId", simProductId);
        params.put("author", auditorAware.getCurrentAuditor());
        return getNamedParameterJdbcTemplate().update(DELETE_PRODUCT, params);
    }

    @Override
    public List<SimProduct> getSimProductsByEventId(Integer eventId) {
        return getNamedParameterJdbcTemplate().query(GET_PRODUCTS_BY_EVENT, Collections.singletonMap("eventId", eventId), new BeanPropertyRowMapper<>(SimProduct.class));
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("Sim_Product");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ID");
    }
}

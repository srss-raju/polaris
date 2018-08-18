package us.deloitteinnovation.polaris.evaluator.product.dao.internal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.evaluator.product.dao.IProductDAO;
import us.deloitteinnovation.polaris.evaluator.product.model.Product;
import us.deloitteinnovation.polaris.evaluator.product.model.SearchResultResponse;
import us.deloitteinnovation.polaris.common.util.Constant;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Repository
public class ProductDAOImpl extends AbstractDAO implements IProductDAO {

    private static final String GET_ALL_PRODUCTS =
            "SELECT p.id AS Product_ID,P.Product_Name " +
                    "FROM [dbo].[tbl_Product_Master] p " +
                    "INNER JOIN [dbo].[tbl_Promo] pr " +
                    "ON p.id = pr.product_id " +
                    "WHERE LOWER(p.[Product_Name]) <> 'null' GROUP BY  p.id,p.Product_Name";

    private static final String COMPETITOR_FLAG_OFF = "0";

    private static final String PRODUCT_SEARCH_QUERY =
            "with product_list as "+
            "( "+
            " select row_number() over(order by product_name,Id desc) as rownumber, "+
            " [Id] as productId, "+
            " [Client_Product_ID] as clientProductID, "+
            " [Product_List_Price] as productListPrice, "+
            " [Product_Name] as productName, "+
            " [Product_Level_1] as productLevel1, "+
            " [Product_Level_2] as productLevel2, "+
            " [Product_Level_3] as productLevel3, "+
            " [Product_Level_4] as productLevel4, "+
            " [Product_Level_5] as productLevel5, "+
            //" [Product_Level_6] as productLevel6, "+
            //" [Product_Level_7] as productLevel7, "+
            " [Product_Attribute_1] as productAttribute1, "+
            " [Product_Attribute_2] as productAttribute2, "+
            " [Product_Attribute_3] as productAttribute3, "+
            " [Product_Attribute_4] as productAttribute4, "+
            " [Product_Attribute_5] as productAttribute5, "+
            " [Product_List_Price_UoM] as currency"+
            " from dbo.tbl_product_master  "+
            " where ([Competitor_Flag] = "+ COMPETITOR_FLAG_OFF +") and LOWER([Product_Name]) <> 'null' and [Product_Name] is not null and ([Product_Name] LIKE :SEARCH_TEXT or "+
            " product_level_1 LIKE :SEARCH_TEXT or  "+
            " product_level_2 LIKE :SEARCH_TEXT or  "+
            " product_level_3 LIKE :SEARCH_TEXT or  "+
            " product_level_4 LIKE :SEARCH_TEXT or  "+
            " product_level_5 LIKE :SEARCH_TEXT or  "+
            //" product_level_6 LIKE :SEARCH_TEXT or  "+
            //" product_level_7 LIKE :SEARCH_TEXT or  "+
            " product_attribute_1 LIKE :SEARCH_TEXT or  "+
            " product_attribute_2 LIKE :SEARCH_TEXT or  "+
            " product_attribute_3 LIKE :SEARCH_TEXT or  "+
            " product_attribute_4 LIKE :SEARCH_TEXT or  "+
            " product_attribute_5 LIKE :SEARCH_TEXT )  "+
            ")  " ;

    private static final String PRODUCT_SEARCH_RESULT =
                    PRODUCT_SEARCH_QUERY +
                    "select * "+
                    "from product_list  "+
                    "where rownumber >= :startRowId and rownumber <= :endRowId ";

    private static final String PRODUCT_SEARCH_RESULT_COUNT = PRODUCT_SEARCH_QUERY +
                    "select count(*) " +
                    "from product_list ";

    private static final String GET_PRODUCTS_BY_ID = "SELECT TOP 10 [ID] as productId, [Client_Product_ID] as clientProductId, [Product_Name] as productName" +
            " FROM [dbo].[tbl_Product_Master] WHERE [Client_Product_ID] like :clientProductId AND [Product_Name] != 'NULL'";


    @Override
    public List<Product> getAllProduct() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_PRODUCTS, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getProductsByClientProductID(String clientProductId) {
        return getNamedParameterJdbcTemplate().query(GET_PRODUCTS_BY_ID,
                Collections.singletonMap("clientProductId", clientProductId + '%'),
                new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public SearchResultResponse findProductDetailsBySearchText(String searchText, Integer offSet, Integer size) {
        SearchResultResponse sRR = new SearchResultResponse();
        List<Product> searchList = getNamedParameterJdbcTemplate().query(PRODUCT_SEARCH_RESULT, prepareMapSqlParameterSource(searchText, offSet, size), new BeanPropertyRowMapper<>(Product.class));
        sRR.setProducts(searchList);
        sRR.setCount(findProductDetailsSizeBySearchText(searchText));
        return sRR;

    }

    private MapSqlParameterSource prepareMapSqlParameterSource(String searchText, Integer offSet, Integer size) {
        int _offSet = (offSet < 1) ? Constant.DEFAULT_OFFSET : offSet;
        int _size = (size < 1) ? Constant.DEFAULT_SIZE : size;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(Constant.SEARCH_TEXT, '%' + searchText + '%');
        params.addValue(Constant.OFF_SET, _offSet);
        params.addValue(Constant.PAGE_SIZE, _size);
        return params;
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("tbl_Product_Master");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ID");
    }

    @Override
    public Integer findProductDetailsSizeBySearchText(String searchText) {
        return getNamedParameterJdbcTemplate().queryForObject(PRODUCT_SEARCH_RESULT_COUNT, prepareMapSqlParameterSource(searchText, 0, 0), Integer.class);
    }
}

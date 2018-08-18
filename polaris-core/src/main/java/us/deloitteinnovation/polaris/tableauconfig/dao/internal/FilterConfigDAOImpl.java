package us.deloitteinnovation.polaris.tableauconfig.dao.internal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.tableauconfig.dao.IFilterConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.ClientConfig;
import us.deloitteinnovation.polaris.tableauconfig.model.Filter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Repository
public class FilterConfigDAOImpl extends AbstractDAO implements IFilterConfigDAO {

    private final static String GET_ALL_FILTERS = "SELECT [Id],[config_id],[group_config_Id] FROM [dbo].[app_Filter_Config]";
    private final static String GET_FILTER_BY_ID = "SELECT [Id],[config_id] ,[group_config_Id] FROM [dbo].[app_Filter_Config] WHERE [Id]=:filterId";
    private final static String GET_FILTER_BY_GROUP_CONFIG = "SELECT [Id],[config_id] ,[group_config_Id] FROM [dbo].[app_Filter_Config] WHERE [group_config_Id]=:groupId";
    private final static String INSERT_FILTER = "INSERT INTO [dbo].[app_Filter_Config]  ([config_id],[group_config_Id]) OUTPUT Inserted.id VALUES (:config_id, :groupId)";
    private final static String INSERT_FILTERS = "INSERT INTO [dbo].[app_Filter_Config]  ([config_id],[group_config_Id]) VALUES (:config_id, :groupId)";
    private final static String CLIENT_TABLE_NAMES = "clientTableNameValues";
    private final static String GET_CLIENT_CONFIG = "SELECT [ID] AS id, [Table_Name] as tableName, [Column_Name] as columnName ,[Client_Label] AS clientLabel FROM [dbo].[tbl_Client_Config] WHERE [Table_Name] IN (:"+CLIENT_TABLE_NAMES+")";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Filter_Config");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    @Override
    public List<Filter> getAllFilters() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_FILTERS, rs -> {
            List<Filter> filters = new ArrayList<>();
            while (rs.next()){
                filters.add(extractFilterFromResultSet(rs));
            }
            return filters;
        });
    }

    @Override
    public Filter getFilterById(Integer filterId) {
        return getNamedParameterJdbcTemplate().query(GET_FILTER_BY_ID, Collections.singletonMap("filterId", filterId), rs -> rs.next() ? extractFilterFromResultSet(rs) : null);
    }

    @Override
    public List<Filter> getFilterByGroupConfig(Integer groupConfigId) {
        return getNamedParameterJdbcTemplate().query(GET_FILTER_BY_GROUP_CONFIG,Collections.singletonMap("groupId", groupConfigId) , rs -> {
            List<Filter> filters = new ArrayList<>();
            while (rs.next()){
                filters.add(extractFilterFromResultSet(rs));
            }
            return filters;
        });
    }

    @Override
    public Integer insertFilter(Filter filter) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("config_id", filter.getConfig_id());
        paramMap.put("groupId", filter.getGroupId());
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_FILTER, paramMap, Integer.class);
    }

    @Override
    public int[] insertFilters(List<Filter> filters) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(filters.toArray());
        return getNamedParameterJdbcTemplate().batchUpdate(INSERT_FILTERS, batch);
    }

    private Filter extractFilterFromResultSet(ResultSet rs) throws SQLException {
        Filter filter = new Filter();
        filter.setId(rs.getInt("Id"));
        filter.setConfig_id(rs.getInt("config_id"));
        filter.setGroupId(rs.getInt("group_config_Id"));
        return filter;
    }

    @Override
    public List<ClientConfig> getClientConfigs(){
        List<String> tableNamesList = new ArrayList<>();
        tableNamesList.add("tbl_Customer_Master");
        tableNamesList.add("tbl_Product_Master");
        Map tableNamesMap = Collections.singletonMap(CLIENT_TABLE_NAMES,tableNamesList);
        List<ClientConfig>  clientConfigList= getNamedParameterJdbcTemplate().query(GET_CLIENT_CONFIG, tableNamesMap, new BeanPropertyRowMapper(ClientConfig.class));
        return clientConfigList;
    }
}

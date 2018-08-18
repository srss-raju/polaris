package us.deloitteinnovation.polaris.tableauconfig.dao.internal;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.tableauconfig.dao.IParameterConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Repository
public class ParameterConfigDAOImpl extends AbstractDAO implements IParameterConfigDAO {

    private final static String GET_ALL_PARAMETERS = "SELECT [Id] ,[name],[tableau_config_Id], [config_Id] FROM [dbo].[app_Parameter_Config]";
    private final static String GET_PARAMETER_BY_ID = "SELECT [Id] ,[name],[tableau_config_Id], [config_Id] FROM [dbo].[app_Parameter_Config] WHERE [Id]= :parameterId";
    private final static String GET_PARAMETER_BY_TABLEAU_CONFIG = "SELECT [Id] ,[name],[tableau_config_Id], [config_Id] FROM [dbo].[app_Parameter_Config] WHERE [tableau_config_Id]= :tableauConfigId";
    private final static String INSERT_PARAMETER = "INSERT INTO [dbo].[app_Parameter_Config] ([name],[tableau_config_Id], [config_Id]) OUTPUT Inserted.id VALUES (:name, :tableau_config_Id, :config_id)";
    private final static String INSERT_PARAMETERS = "INSERT INTO [dbo].[app_Parameter_Config] ([name],[tableau_config_Id], [config_Id]) VALUES (:name, :tableauId, :config_id)";
    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_ParameterConfig");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ParameterConfigId");
    }

    @Override
    public List<Parameter> getAllParameters() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_PARAMETERS, rs -> {
            List<Parameter> parameters = new ArrayList<>();
            while (rs.next()) {
                parameters.add(extractParameterFromResultSet(rs));
            }
            return parameters;
        });
    }

    @Override
    public Parameter getParameterById(Integer parameterId) {
        return getNamedParameterJdbcTemplate().query(GET_PARAMETER_BY_ID, Collections.singletonMap("parameterId", parameterId), rs -> rs.next() ? extractParameterFromResultSet(rs) : null);
    }

    @Override
    public List<Parameter> getParametersByTableauConfig(Integer tableauId) {
        return getNamedParameterJdbcTemplate().query(GET_PARAMETER_BY_TABLEAU_CONFIG, Collections.singletonMap("tableauConfigId", tableauId), rs -> {
            List<Parameter> parameters = new ArrayList<>();
            while (rs.next()) {
                parameters.add(extractParameterFromResultSet(rs));
            }
            return parameters;
        });
    }

    @Override
    public Integer insertParameter(Parameter parameter) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", parameter.getName());
        paramMap.put("tableau_config_Id", parameter.getTableauId());
        paramMap.put("config_id", parameter.getConfig_id());
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_PARAMETER, paramMap, Integer.class);
    }

    @Override
    public int[] insertParameters(List<Parameter> parameters) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(parameters.toArray());
        return getNamedParameterJdbcTemplate().batchUpdate(INSERT_PARAMETERS, batch);
    }

    private Parameter extractParameterFromResultSet(ResultSet rs) throws SQLException {
        Parameter parameter = new Parameter();
        parameter.setId(rs.getInt("Id"));
        parameter.setName(rs.getString("name"));
        parameter.setTableauId(rs.getInt("tableau_config_Id"));
        parameter.setConfig_id(rs.getInt("config_Id"));
        return parameter;
    }

}

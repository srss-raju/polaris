package us.deloitteinnovation.polaris.role.dao.internal;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.role.dao.IRoleDAO;
import us.deloitteinnovation.polaris.role.model.Role;

import java.util.*;

/**
 * Created by rbentaarit on 2/27/2017.
 */
@Repository
public class RoleDAOImpl extends AbstractDAO implements IRoleDAO  {

    private static final String GET_ROLES = "SELECT [Id],[Name] FROM [dbo].[app_Role]";

    private static final String INSERT_ROLE_MODULE = "INSERT INTO [dbo].[app_Role_Module] (Module_Id, Role_Id) values (:moduleId, :roleId)";

    private static final String DELETE_ROLE_MODULE_BY_MODULE_ID = "DELETE FROM [dbo].[app_Role_Module] WHERE  [dbo].[app_Role_Module].[Module_Id] = :moduleId";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Role");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    @Override
    public List<Role> getRoles() {
        return getNamedParameterJdbcTemplate().query(GET_ROLES, (rs, rowNum) -> new Role(rs.getInt("Id"), rs.getString("Name")));
    }

    @Override
    public void insertRoleModule(Integer moduleId, List<Role> roles) {
        List<Map<String, Object>> batchValues = new ArrayList<>(roles.size());
        roles.stream().forEach(role -> batchValues.add(new MapSqlParameterSource("moduleId", moduleId).addValue("roleId", role.getId()).getValues()));
       getNamedParameterJdbcTemplate().batchUpdate(INSERT_ROLE_MODULE, batchValues.toArray(new Map[roles.size()]));
    }

    @Override
    public void updateRoleModule(Integer moduleId, List<Role> roles) {
      //Todo update role module
        // List<Map<String, Object>> batchValues = new ArrayList<>(roles.size());
    }

    @Override
    public void deleteRoleModuleByModuleId(Integer moduleId) {
       getNamedParameterJdbcTemplate().update(DELETE_ROLE_MODULE_BY_MODULE_ID, Collections.singletonMap("moduleId", moduleId));
    }


}

package us.deloitteinnovation.polaris.role.dao;

import us.deloitteinnovation.polaris.role.model.Role;

import java.util.List;

/**
 * Created by rbentaarit on 2/27/2017.
 */
public interface IRoleDAO {
    List<Role> getRoles();
    void insertRoleModule(Integer moduleId, List<Role> roles);
    void updateRoleModule(Integer moduleId, List<Role> roles);
    void deleteRoleModuleByModuleId(Integer moduleId);
}

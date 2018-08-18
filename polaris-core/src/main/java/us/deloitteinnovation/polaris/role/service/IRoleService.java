package us.deloitteinnovation.polaris.role.service;

import us.deloitteinnovation.polaris.role.model.Role;

import java.util.List;

/**
 * Created by rbentaarit on 2/27/2017.
 */
@FunctionalInterface
public interface IRoleService {

    List<Role> getRoles();
}

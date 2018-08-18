package us.deloitteinnovation.polaris.role.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.role.dao.IRoleDAO;
import us.deloitteinnovation.polaris.role.model.Role;
import us.deloitteinnovation.polaris.role.service.IRoleService;

import java.util.List;

/**
 * Created by rbentaarit on 2/27/2017.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }
}

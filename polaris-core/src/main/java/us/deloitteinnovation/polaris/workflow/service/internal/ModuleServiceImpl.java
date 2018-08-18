package us.deloitteinnovation.polaris.workflow.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.role.dao.IRoleDAO;
import us.deloitteinnovation.polaris.workflow.dao.IModuleDAO;
import us.deloitteinnovation.polaris.workflow.model.Module;
import us.deloitteinnovation.polaris.workflow.service.IModuleService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */

@Service
public class ModuleServiceImpl implements IModuleService{

    private final IModuleDAO moduleDAO;
    private final IRoleDAO roleDAO;

    @Autowired
    public ModuleServiceImpl(IModuleDAO moduleDAO, IRoleDAO roleDAO) {
        this.moduleDAO = moduleDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public Module insert(Module module) {
        module.setSortIndex((short) (moduleDAO.getMaxModuleSortIndex() + 1));
        Integer moduleId = moduleDAO.insertModuleReturnId(module);
        roleDAO.insertRoleModule(moduleId, module.getRoles());
        return moduleDAO.getModule(moduleId);
    }

    @Override
    public Module getModule(Integer moduleId) {
        return moduleDAO.getModule(moduleId);
    }

    @Override
    public List<Module> getModules() {
        return moduleDAO.getAllModules();
    }

    @Override
    @Transactional
    public Module updateModule(Integer moduleId, Module module) {
        roleDAO.deleteRoleModuleByModuleId(moduleId);
        roleDAO.insertRoleModule(moduleId, module.getRoles());
        return moduleDAO.updateModule(moduleId, module);
    }

    @Override
    public void deleteModule(Integer moduleId) {
        moduleDAO.deleteModule(moduleId);
    }
}

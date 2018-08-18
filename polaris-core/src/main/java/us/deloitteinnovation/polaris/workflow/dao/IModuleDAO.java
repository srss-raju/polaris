package us.deloitteinnovation.polaris.workflow.dao;

import us.deloitteinnovation.polaris.workflow.model.Module;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IModuleDAO {

    List<Module> getAllModules();
    Module getModule(Integer moduleId);
    Module insertModule(Module module);
    Integer insertModuleReturnId(Module module);
    Module updateModule(Integer moduleId, Module module);
    void deleteModule(Integer moduleId);
    Short getMaxModuleSortIndex();
}

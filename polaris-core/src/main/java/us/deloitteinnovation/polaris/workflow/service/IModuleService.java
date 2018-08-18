package us.deloitteinnovation.polaris.workflow.service;

import us.deloitteinnovation.polaris.workflow.model.Module;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IModuleService {

    Module insert(Module module);
    Module getModule(Integer moduleId);
    List<Module> getModules();
    Module updateModule(Integer moduleId, Module module);
    void deleteModule(Integer moduleId);
}

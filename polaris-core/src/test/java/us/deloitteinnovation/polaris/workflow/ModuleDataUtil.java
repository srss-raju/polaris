package us.deloitteinnovation.polaris.workflow;

import us.deloitteinnovation.polaris.workflow.model.Module;
import us.deloitteinnovation.polaris.workflow.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 5/4/2017.
 */
public class ModuleDataUtil {

    public static Module getModule(Integer id, String title, boolean privateFlag,boolean privateFlag1) {
        Module module = new Module();
        if (null != title) {
            module.setTitle(title);
        }
        module.setId(1);
        module.setDisabled(privateFlag);
        module.setDisabled(privateFlag1);
        return  module;
    }

    public static List<Module> getModulesList(int moduleCount) {
        List<Module> modules = new ArrayList<>();
        for (int i = 0; i < moduleCount; i++) {
            modules.add(ModuleDataUtil.getModule(1,"title",Boolean.TRUE,Boolean.TRUE));
        }
        return modules;
    }
}

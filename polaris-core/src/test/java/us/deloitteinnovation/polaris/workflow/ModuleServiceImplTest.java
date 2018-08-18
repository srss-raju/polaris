package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import us.deloitteinnovation.polaris.alerts.dao.internal.AlertsDAOImpl;
import us.deloitteinnovation.polaris.alerts.service.IAlertsService;
import us.deloitteinnovation.polaris.alerts.service.internal.AlertsServiceImpl;
import us.deloitteinnovation.polaris.bookmarks.BookmarkDataUtil;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.role.dao.internal.RoleDAOImpl;
import us.deloitteinnovation.polaris.role.model.Role;
import us.deloitteinnovation.polaris.workflow.dao.internal.ModuleDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Module;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.service.IModuleService;
import us.deloitteinnovation.polaris.workflow.service.internal.ModuleServiceImpl;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;

/**
 * Created by mgundlapally on 5/4/2017.
 */
public class ModuleServiceImplTest extends AbstractTest {

    @Mock
    private ModuleDAOImpl moduleDAO;

    @Mock
    private RoleDAOImpl roleDAO;
    

    private IModuleService moduleService;

    @Before
    public void setup() {
        moduleService = new ModuleServiceImpl(moduleDAO, roleDAO);
    }

    @Test
    public void getModules() {
        List<Module> modules = ModuleDataUtil.getModulesList(5);
        Mockito.when(moduleDAO.getAllModules()).thenReturn(modules);
        Assert.assertEquals(modules, moduleService.getModules());

    }

    @Test
    public void getModuleId() {

        Module module = ModuleDataUtil.getModule(1, "title", Boolean.TRUE, Boolean.TRUE);
        Mockito.when(moduleDAO.getModule(anyInt())).thenReturn(module);
        Assert.assertEquals(module, moduleService.getModule(anyInt()));
    }

   
    @Test
    public void getModuleUpdate() {
        Module module = ModuleDataUtil.getModule(1, "title", Boolean.TRUE, Boolean.TRUE);
        Mockito.when(moduleDAO.updateModule(1,module)).thenReturn(module);
        Module module1 = moduleService.updateModule(1,module);
        Assert.assertNotNull(module1);
        Assert.assertEquals(module, module1);

    }

}
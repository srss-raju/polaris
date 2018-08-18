package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.dao.IModuleDAO;
import us.deloitteinnovation.polaris.workflow.dao.IQuestionDAO;
import us.deloitteinnovation.polaris.workflow.dao.internal.ModuleDAOImpl;
import us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Module;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mgundlapally on 11-05-2017.
 */

@Component
public class ModuleDAOTest extends AbstractTest {

    private IModuleDAO moduleDAO;

    @Before
    public void setup() {
        moduleDAO = new ModuleDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void testgetModuleList() {
        List<Module> moduleList = moduleDAO.getAllModules();
        Assert.assertNotNull(moduleList);
        Assert.assertTrue(moduleList.size() > 0);
    }

    @Test
    public void testgetModuleId() throws SQLException {
        Module module = ModuleDataUtil.getModule(1,"moduleName",true,true);
        Module result = moduleDAO.getModule(1);
        Assert.assertEquals(module.getId(),result.getId());
    }

    @Test
    public void testgetMaxId() throws SQLException {
        Module module = ModuleDataUtil.getModule(1,"moduleName",true,true);
         Short aShort= moduleDAO.getMaxModuleSortIndex();
        Assert.assertNotEquals(module,aShort);

    }

}

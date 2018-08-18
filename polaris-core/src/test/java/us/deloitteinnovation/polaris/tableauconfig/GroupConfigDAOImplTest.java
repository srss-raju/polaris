package us.deloitteinnovation.polaris.tableauconfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.bookmarks.BookmarksDAOTest;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.tableauconfig.dao.internal.FilterConfigDAOImpl;
import us.deloitteinnovation.polaris.tableauconfig.dao.internal.GroupConfigDAOImpl;
import us.deloitteinnovation.polaris.tableauconfig.model.Filter;
import us.deloitteinnovation.polaris.tableauconfig.model.Group;

import java.util.List;

/**
 * Created by mgundlapally on 08-06-2017.
 */

@Component
public class GroupConfigDAOImplTest extends AbstractTest {


    private static final Logger LOG = LoggerFactory.getLogger(GroupConfigDAOImpl.class);

    private GroupConfigDAOImpl groupConfigDAO;


    @Before
    public void setup() {
        this.groupConfigDAO = new GroupConfigDAOImpl();
        this.groupConfigDAO.setDatasource(dataSource);

    }

    @Test
    public void getAllGroupsTest() {
        List<Group> groups = groupConfigDAO.getAllGroups();
        Assert.assertNotNull(groups);
        Assert.assertTrue(groups.size() >0);
    }

    @Test
    public void getGroupByIdTest() {
        Group groups = groupConfigDAO.getGroupById(1);
        Assert.assertNotNull(groups);
        Assert.assertTrue(groups.getId()>0);
    }


    @Test
    public void getGroupBySheetConfigTest() {
        List<Group> groups = groupConfigDAO.getGroupBySheetConfig(1);
        Assert.assertNotNull(groups);
        Assert.assertTrue(groups.size() > 0);
    }

    @Test
    public void insertGroupTest() {
        Group groups=new Group();
        groups.setName("groupName");
        groups.setSheetId(2);
        Integer i= groupConfigDAO.insertGroup(groups);
        Assert.assertNotNull(i);
        LOG.info("Result ----->> ", i);

    }

}

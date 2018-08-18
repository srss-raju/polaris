package us.deloitteinnovation.polaris.tableauconfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.bookmarks.BookmarksDAOTest;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.tableauconfig.dao.internal.GroupConfigDAOImpl;
import us.deloitteinnovation.polaris.tableauconfig.dao.internal.TableauConfigDAOImpl;
import us.deloitteinnovation.polaris.tableauconfig.model.Group;
import us.deloitteinnovation.polaris.tableauconfig.model.Tableau;

import java.util.List;

/**
 * Created by mgundlapally on 08-06-2017.
 */
@Component
public class TableauConfigDAOImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(TableauConfigDAOImplTest.class);

    private TableauConfigDAOImpl tableauConfigDAO;


    @Before
    public void setup() {
        this.tableauConfigDAO = new TableauConfigDAOImpl();
        this.tableauConfigDAO.setDatasource(dataSource);

    }

    @Test
    public void getAllTableauTest() {
        List<Tableau> tableaus = tableauConfigDAO.getAllTableau();
        Assert.assertNotNull(tableaus);
        Assert.assertTrue(tableaus.size() >0);
    }

    @Test
    public void getTableauByIdTest() {
        Tableau tableau = tableauConfigDAO.getTableauById(1);
        Assert.assertNotNull(tableau);
        Assert.assertTrue(tableau.getId()>0);
    }
    @Test
    public void insertTableauTest() {
        Tableau tableau=new Tableau();
        tableau.setId(1);
        tableau.setName("nameTableau");
        tableau.setLink("Link");
        Integer integer = tableauConfigDAO.insertTableau(tableau);
        Assert.assertNotNull(integer);
        LOG.info("Result ----->> ", integer);

    }
}

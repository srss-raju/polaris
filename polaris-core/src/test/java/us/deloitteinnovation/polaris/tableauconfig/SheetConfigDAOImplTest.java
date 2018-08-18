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
import us.deloitteinnovation.polaris.tableauconfig.dao.internal.SheetConfigDAOImpl;
import us.deloitteinnovation.polaris.tableauconfig.model.Group;
import us.deloitteinnovation.polaris.tableauconfig.model.Sheet;

import java.util.List;

/**
 * Created by mgundlapally on 08-06-2017.
 */
@Component
public class SheetConfigDAOImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(SheetConfigDAOImplTest.class);

    private SheetConfigDAOImpl sheetConfigDAO;


    @Before
    public void setup() {
        this.sheetConfigDAO = new SheetConfigDAOImpl();
        this.sheetConfigDAO.setDatasource(dataSource);

    }

    @Test
    public void getAllSheetsTest() {
        List<Sheet> sheets = sheetConfigDAO.getAllSheets();
        Assert.assertNotNull(sheets);
        Assert.assertTrue(sheets.size() >0);
    }

    @Test
    public void getSheetByIdTest() {
         Sheet sheet = sheetConfigDAO.getSheetById(1);
        Assert.assertNotNull(sheet);
        Assert.assertTrue(sheet.getId()>0);
    }

    @Test
    public void getSheetsByTableauConfigTest() {
        List<Sheet> sheets = sheetConfigDAO.getSheetsByTableauConfig(1);
        Assert.assertNotNull(sheets);
        Assert.assertTrue(sheets.size() >0);
    }
    @Test
    public void insertSheetTest() {
        Sheet sheet=new Sheet();
        sheet.setId(22);
        sheet.setName("sheetName");
        sheet.setTableauId(1);
        Integer i = sheetConfigDAO.insertSheet(sheet);
        Assert.assertNotNull(i);
        LOG.info("Result ----->> ", i);

    }
}

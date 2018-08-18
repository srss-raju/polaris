package us.deloitteinnovation.polaris.menu;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.menu.dao.IMenuDAO;
import us.deloitteinnovation.polaris.menu.dao.internal.MenuDAOImpl;

@Component
public class MenuDAOIT extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(MenuDAOIT.class);

    @Mock
    private JdbcTemplate jdbcTemplate;

    private IMenuDAO menuDAO;

    @Before
    public void setup () {
        menuDAO = new MenuDAOImpl(jdbcTemplate);
    }

    @Test
    public void testGetMenuDetails() {
        JSONObject result = menuDAO.getMenuDetails();
        LOG.info("result --->> "+result);
    }

}

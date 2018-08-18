package us.deloitteinnovation.polaris.alerts;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.alerts.dao.IAlertsDAO;
import us.deloitteinnovation.polaris.alerts.dao.internal.AlertsDAOImpl;
import us.deloitteinnovation.polaris.common.AbstractTest;


@Component
public class AlertsDAOTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(AlertsDAOTest.class);

    private IAlertsDAO alertsDAO;


    @Before
    public void setup() {
        alertsDAO = new AlertsDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void getAllAlerts() {
        JSONObject jsonObject=alertsDAO.getAlertsDetail();
        Assert.assertNotNull(jsonObject);
        Assert.assertTrue(jsonObject.size() > 0);
    }

    @Test
    public void getCustomerDetails() {
       JSONObject jsonObject= alertsDAO.getCustomerDetails("All");
        Assert.assertNotNull(jsonObject);
        Assert.assertTrue(jsonObject.size() > 0);
    }

    @Test
    public void getCustomerDetailsException() {
       JSONObject jsonObject= alertsDAO.getCustomerDetails("%");
        Assert.assertNotNull(jsonObject);
        Assert.assertTrue(jsonObject.size() > 0);
    }


    @Test
    public void getChannelDetails() {
    	JSONObject result = alertsDAO.getChannelDetails("Specialty");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void getChannelDetailsException() {
        JSONObject result=alertsDAO.getChannelDetails(null);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);

    }

}

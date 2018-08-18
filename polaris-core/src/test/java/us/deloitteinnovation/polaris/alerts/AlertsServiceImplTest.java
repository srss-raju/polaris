package us.deloitteinnovation.polaris.alerts;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.alerts.dao.internal.AlertsDAOImpl;
import us.deloitteinnovation.polaris.alerts.model.AlertsVO;
import us.deloitteinnovation.polaris.alerts.service.IAlertsService;
import us.deloitteinnovation.polaris.alerts.service.internal.AlertsServiceImpl;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.ModuleDataUtil;
import us.deloitteinnovation.polaris.workflow.model.Module;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

@Component
public class AlertsServiceImplTest extends AbstractTest {

	@Mock
	private AlertsDAOImpl alertsDAOImpl;

	private IAlertsService alertsService;

	@Before
	public void setup() {
		alertsService = new AlertsServiceImpl(alertsDAOImpl);
	}
	
	@Test
	public void getAllAlerts() {
		alertsService.getAlertsDetail();

	}
	
	@Test
	public void getCustomerDetails() {
		alertsService.getCustomerDetails("name");

	}
	
	@Test
	public void getChannelDetails() {
		alertsService.getChannelDetails("chanelDetail");
	}


	

}

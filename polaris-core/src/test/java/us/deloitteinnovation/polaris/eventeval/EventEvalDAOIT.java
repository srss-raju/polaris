package us.deloitteinnovation.polaris.eventeval;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.eventeval.dao.IEventEvalDAO;
import us.deloitteinnovation.polaris.eventeval.dao.internal.EventEvalDAOImpl;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;
import us.deloitteinnovation.polaris.common.util.PolarisUtil;

import java.text.ParseException;
import java.util.Date;

@Component
public class EventEvalDAOIT extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(EventEvalDAOIT.class);

    @Mock
    private JdbcTemplate jdbcTemplate;

    private IEventEvalDAO eventEvalDAO;

	@Before
	public void setup () {
		eventEvalDAO = new EventEvalDAOImpl(jdbcTemplate);
	}

    @Test
	@Ignore
    public void testGetEventOptimizerPromotionDetails() {
        String details = eventEvalDAO.getEventOptimizerPromotionDetails(936, 844, 1);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerPromotionDetailsNotSimulated() {
        String details = eventEvalDAO.getEventOptimizerPromotionDetails(1033, 844, 2);
        LOG.info("Result ----->> ", details);
    }

    @Test
	@Ignore
    public void testSaveEventOptimizerPromotionDetails() {
        EventEvalPromotionVO eOptimPromotionVO = new EventEvalPromotionVO();
        eOptimPromotionVO.setProductId(936);
        eOptimPromotionVO.setPromotionId(1);
        eOptimPromotionVO.setSimulatedEndDate(new Date());
        eOptimPromotionVO.setSimulatedStartDate(new Date());
		eOptimPromotionVO.setSimulatedPromotionDiscountDepth(2);
		eOptimPromotionVO.setSimulatedPromotionTactic("Display");
		eOptimPromotionVO.setSimulatedPromotionUnits(10);
		eOptimPromotionVO.setSimulatedVolumeLiftCoefficient(2.0f);
		eOptimPromotionVO.setSimulatedPromotionPrice(100);
        eOptimPromotionVO.setSimulated(true); 

        JSONObject details = eventEvalDAO.saveEventOptimizerPromotionDetails(eOptimPromotionVO);
        LOG.info("Result ----->> ", details);
    }

    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetails() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic1");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic2() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic2");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic3() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic3");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic4() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic4");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic5() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic5");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic6() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic6");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic7() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic7");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }
    
    @Test
	@Ignore
    public void testGetEventOptimizerSimulatedPromotionDetailsWithTactic8() {
    	EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("tactic8");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(0);
    	eventEvalSimulatedPromotionVO.setDepthValue(20);
    	try {
			eventEvalSimulatedPromotionVO.setSimulatedStartDate(PolarisUtil.getDate("22-04-2016"));
			eventEvalSimulatedPromotionVO.setSimulatedEndDate(PolarisUtil.getDate("27-04-2016"));
		} catch (ParseException e) {
			LOG.info(e.getMessage());
		}
        String details = eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
        LOG.info("Result ----->> ", details);
    }

}

package us.deloitteinnovation.polaris.eventeval;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.eventeval.dao.internal.EventEvalDAOImpl;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;
import us.deloitteinnovation.polaris.eventeval.service.IEventEvalService;
import us.deloitteinnovation.polaris.eventeval.service.internal.EventEvalServiceImpl;

import java.util.Date;

@Component
public class EventEvalServiceImplTest extends AbstractTest {

	@Mock
	private EventEvalDAOImpl eventEvalDAO;

	

	private IEventEvalService eventEvalService;

	@Before
	public void setupService() {
		eventEvalService = new EventEvalServiceImpl(eventEvalDAO);
	}
	
	@Test
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
		eventEvalService.saveEventOptimizerPromotionDetails(eOptimPromotionVO);
		//Mockito.when(eventEvalService.saveEventOptimizerPromotionDetails(eventEvalPromotionVO)).thenReturn(new JSONObject()); 
	}
	
	@Test
	public void testGetEventOptimizerPromotionDetails() {
		 eventEvalService.getEventOptimizerPromotionDetails(936, 844, 1);
		//Mockito.when(eventEvalService.getEventOptimizerPromotionDetails(936, 844, 1, "COUPON")).thenReturn("");
	}
	
	@Test
	public void testGetEventOptimizerSimulatedPromotionDetails(){
		EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO = new EventEvalSimulatedPromotionVO();
    	eventEvalSimulatedPromotionVO.setProductId(936);
    	eventEvalSimulatedPromotionVO.setCustomerId(844);
    	eventEvalSimulatedPromotionVO.setPromotionId(1);
    	eventEvalSimulatedPromotionVO.setTacticFlag(1);
    	eventEvalSimulatedPromotionVO.setTactic("Display");
    	eventEvalSimulatedPromotionVO.setDaysFlag(0);
    	eventEvalSimulatedPromotionVO.setDaysValue(0);
    	eventEvalSimulatedPromotionVO.setDepthFlag(1);
    	eventEvalSimulatedPromotionVO.setDepthValue(10);
    	eventEvalSimulatedPromotionVO.setSimulatedEndDate(new Date());
    	eventEvalSimulatedPromotionVO.setSimulatedStartDate(new Date());
    	eventEvalService.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
    	//Mockito.when(eventEvalService.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO)).thenReturn(new JSONObject());
	}
	
}

package us.deloitteinnovation.polaris.priceeval;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.priceeval.dao.internal.PriceEvalDAOImpl;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;
import us.deloitteinnovation.polaris.priceeval.service.IPriceEvalService;
import us.deloitteinnovation.polaris.priceeval.service.internal.PriceEvalServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceEvalServiceImplTest extends AbstractTest {

	@Mock
	private PriceEvalDAOImpl priceEvalDAO;

	private IPriceEvalService priceEvalService;

	@Before
	public void setup() {
		priceEvalService = new PriceEvalServiceImpl(priceEvalDAO);
	}

	@Test
	public void testSavePriceEvaluatorDetails() {
		 List<PriceEvaluatorVO> priceEvaluatorVOList = new ArrayList<PriceEvaluatorVO>();
	        PriceEvaluatorVO priceEvalutorVO1 = new PriceEvaluatorVO();
	        priceEvalutorVO1.setCustomerId(275);
	        priceEvalutorVO1.setProductId(967);
	        priceEvalutorVO1.setSimulatedShelfPrice(8.24);
	        PriceEvaluatorVO priceEvalutorVO2 = new PriceEvaluatorVO();
	        priceEvalutorVO2.setCustomerId(275);
	        priceEvalutorVO2.setProductId(969);
	        priceEvalutorVO2.setSimulatedShelfPrice(2.56);
	        PriceEvaluatorVO priceEvalutorVO3 = new PriceEvaluatorVO();
	        priceEvalutorVO3.setCustomerId(275);
	        priceEvalutorVO3.setProductId(974);
	        priceEvalutorVO3.setSimulatedShelfPrice(3);
	        PriceEvaluatorVO priceEvalutorVO4 = new PriceEvaluatorVO();
	        priceEvalutorVO4.setCustomerId(275);
	        priceEvalutorVO4.setProductId(971);
	        priceEvalutorVO4.setSimulatedShelfPrice(4.44);
	        priceEvaluatorVOList.add(priceEvalutorVO1);
	        priceEvaluatorVOList.add(priceEvalutorVO2);
	        priceEvaluatorVOList.add(priceEvalutorVO3);
	        priceEvaluatorVOList.add(priceEvalutorVO4);
	        priceEvalService.savePriceEvaluatorDetails(priceEvaluatorVOList);
	        //Mockito.when(priceEvalService.savePriceEvaluatorDetails(priceEvaluatorVOList)).thenReturn(new JSONObject());
	}
	
	@Test
	public void testGetPriceEvaluatorDetails() {
		priceEvalService.getPriceEvaluatorDetails(275, "Product_Level_4", "Alkaline", "Product_Attribute_1");   
		//Mockito.when(priceEvalService.getPriceEvaluatorDetails(275, "Product_Level_4", "Alkaline", "Product_Attribute_1")).thenReturn(new JSONObject());
	}
	
	@Test
	public void testGetCustomers(){
		priceEvalService.getCustomers();
		//Mockito.when(priceEvalService.getCustomers()).thenReturn(new JSONObject());
	}
	
}

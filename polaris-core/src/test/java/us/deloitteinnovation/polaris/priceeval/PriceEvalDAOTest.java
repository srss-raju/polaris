package us.deloitteinnovation.polaris.priceeval;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.priceeval.dao.IPriceEvalDAO;
import us.deloitteinnovation.polaris.priceeval.dao.internal.PriceEvalDAOImpl;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceEvalDAOTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PriceEvalDAOTest.class);


    private IPriceEvalDAO priceEvalDAO;

    @Before
    public void setup () {
        priceEvalDAO = new PriceEvalDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test()
    public void testGetPriceEvaluatorDetails() {
        JSONObject jsonObject=priceEvalDAO.getCustomers();
        Assert.assertNotNull(jsonObject);
        Assert.assertTrue(jsonObject.size() > -1);
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
        priceEvaluatorVOList.add(priceEvalutorVO1);
        priceEvaluatorVOList.add(priceEvalutorVO2);
        JSONObject result = priceEvalDAO.savePriceEvaluatorDetails(priceEvaluatorVOList);
        Assert.assertNotNull(result);

    }


}

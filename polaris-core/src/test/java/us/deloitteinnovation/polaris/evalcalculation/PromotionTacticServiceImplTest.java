package us.deloitteinnovation.polaris.evalcalculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTacticDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;
import us.deloitteinnovation.polaris.evalcalculation.service.IPromotionTacticService;
import us.deloitteinnovation.polaris.evalcalculation.service.internal.PromotionTacticServiceImpl;

import java.util.List;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class PromotionTacticServiceImplTest extends AbstractTest {

    @Mock
    private IPromotionTacticDAO promotionTacticDAO;


    private IPromotionTacticService promotionTacticService;

    @Before
    public void setup() {
        promotionTacticService = new PromotionTacticServiceImpl(promotionTacticDAO);
    }

    @Test
    public void getPromotionTactictest() {
        List<PromotionTactic> promotionTactics =  PromotionTacticServiceUtil.getPromotionTacticsList(2);
        Mockito.when(promotionTacticDAO.getAllPromotionTactic()).thenReturn(promotionTactics);
        Assert.assertEquals(promotionTactics, promotionTacticService.getAllTactic());

    }

}

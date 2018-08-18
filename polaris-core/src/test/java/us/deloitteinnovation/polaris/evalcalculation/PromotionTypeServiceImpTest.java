package us.deloitteinnovation.polaris.evalcalculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTypeDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;
import us.deloitteinnovation.polaris.evalcalculation.service.IPromotionTypeService;
import us.deloitteinnovation.polaris.evalcalculation.service.internal.PromotionTypeServiceImpl;

import java.util.List;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class PromotionTypeServiceImpTest extends AbstractTest {

    @Mock
    private IPromotionTypeDAO promotionTypeDAO;


    private IPromotionTypeService promotionTypeService;

    @Before
    public void setup() {
        promotionTypeService = new PromotionTypeServiceImpl(promotionTypeDAO);
    }

    @Test
    public void getPromotionTypeListtest() {
        List<PromotionType> promotionTypes =  PromotionTypeServiceUtil.promotionTypeList(2);
        Mockito.when(promotionTypeDAO.getAllPromotionType()).thenReturn(promotionTypes);
        Assert.assertEquals(promotionTypes, promotionTypeService.getAllPromotionType());

    }
}

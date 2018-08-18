package us.deloitteinnovation.polaris.evalcalculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTacticDAO;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTypeDAO;
import us.deloitteinnovation.polaris.evalcalculation.dao.internal.PromotionTacticDAOImpl;
import us.deloitteinnovation.polaris.evalcalculation.dao.internal.PromotionTypeDAOImpl;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;
import us.deloitteinnovation.polaris.evalcalculation.utils.evalrequest.Promotion;

import java.util.List;

/**
 * Created by mgundlapally on 31-05-2017.
 */
public class PromotionTypeDAOTest extends AbstractTest {

    private IPromotionTypeDAO promotionTypeDAO;

    @Before
    public void setup() {
        promotionTypeDAO = new PromotionTypeDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void getAllPromotionTypeTest() {
        List<PromotionType> promotionTypes = promotionTypeDAO.getAllPromotionType();
        Assert.assertNotNull(promotionTypes);
        Assert.assertTrue(promotionTypes.size() > 0);
    }

}

package us.deloitteinnovation.polaris.evalcalculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTacticDAO;
import us.deloitteinnovation.polaris.evalcalculation.dao.internal.PromotionTacticDAOImpl;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;

import java.util.List;

/**
 * Created by mgundlapally on 31-05-2017.
 */
public class PromotionTacticDAOTest extends AbstractTest{

    private IPromotionTacticDAO promotionTacticDAO;

    @Before
    public void setup() {
        promotionTacticDAO = new PromotionTacticDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void getAllPromotionTacticTest() {
        List<PromotionTactic> promotionTactics = promotionTacticDAO.getAllPromotionTactic();
        Assert.assertNotNull(promotionTactics);
        Assert.assertTrue(promotionTactics.size() > 0);
    }

}

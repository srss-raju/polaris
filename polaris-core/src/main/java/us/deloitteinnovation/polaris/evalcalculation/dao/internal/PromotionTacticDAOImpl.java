package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTacticDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Repository
public class PromotionTacticDAOImpl implements IPromotionTacticDAO {

    private static final String GET_PROMOTIONS = "SELECT * FROM [dbo].[svw_Promo_Tactic]";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PromotionTacticDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PromotionTactic> getAllPromotionTactic() {

    return jdbcTemplate.query(GET_PROMOTIONS, new Object[]{}, rs -> {
        List<PromotionTactic> promotionTactics = new ArrayList<>();
        while (rs.next()){
            PromotionTactic promotionTactic = new PromotionTactic();
            promotionTactic.setName(rs.getString("Promo_Tactic"));
            promotionTactics.add(promotionTactic);
        }
        return promotionTactics;
    });

    }
}

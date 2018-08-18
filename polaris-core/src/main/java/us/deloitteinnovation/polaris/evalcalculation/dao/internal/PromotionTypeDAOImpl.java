package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTypeDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Repository
public class PromotionTypeDAOImpl implements IPromotionTypeDAO {

    private static final String GET_PROMOTION_TACTICS = "SELECT *  FROM [dbo].[svw_Promo_Type]";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PromotionTypeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PromotionType> getAllPromotionType() {
       return jdbcTemplate.query(GET_PROMOTION_TACTICS, new Object[]{}, rs -> {
          List<PromotionType> promotionTypes = new ArrayList<>();
           while (rs.next()){
               PromotionType promotionType = new PromotionType();
               promotionType.setName(rs.getString("Promo_Type"));
               promotionTypes.add(promotionType);
           }
           return promotionTypes;
       });
    }
}

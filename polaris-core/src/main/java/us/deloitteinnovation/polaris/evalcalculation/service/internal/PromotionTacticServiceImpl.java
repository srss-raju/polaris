package us.deloitteinnovation.polaris.evalcalculation.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTacticDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;
import us.deloitteinnovation.polaris.evalcalculation.service.IPromotionTacticService;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Service
public class PromotionTacticServiceImpl implements IPromotionTacticService {

    private final IPromotionTacticDAO promotionTacticDAO;

    @Autowired
    public PromotionTacticServiceImpl(IPromotionTacticDAO IPromotionTacticDAO) {
        this.promotionTacticDAO = IPromotionTacticDAO;
    }

    @Override
    public List<PromotionTactic> getAllTactic() {
        return promotionTacticDAO.getAllPromotionTactic();
    }
}

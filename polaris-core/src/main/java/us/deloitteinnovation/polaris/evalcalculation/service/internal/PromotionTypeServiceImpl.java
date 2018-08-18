package us.deloitteinnovation.polaris.evalcalculation.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionTypeDAO;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;
import us.deloitteinnovation.polaris.evalcalculation.service.IPromotionTypeService;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Service
public class PromotionTypeServiceImpl implements IPromotionTypeService {

    private final IPromotionTypeDAO promotionTypeDAO;

    @Autowired
    public PromotionTypeServiceImpl(IPromotionTypeDAO IPromotionTypeDAO) {
        this.promotionTypeDAO = IPromotionTypeDAO;
    }

    @Override
    public List<PromotionType> getAllPromotionType() {
        return promotionTypeDAO.getAllPromotionType();
    }
}

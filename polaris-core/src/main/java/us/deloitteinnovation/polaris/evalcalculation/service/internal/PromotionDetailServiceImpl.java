package us.deloitteinnovation.polaris.evalcalculation.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evalcalculation.dao.IPromotionDetailDAO;
import us.deloitteinnovation.polaris.evalcalculation.exception.PromotionDetailNotFoundException;
import us.deloitteinnovation.polaris.evalcalculation.model.PromoEffectivenessWhen;
import us.deloitteinnovation.polaris.evalcalculation.model.PromotionDetail;
import us.deloitteinnovation.polaris.evalcalculation.service.IPromotionDetailService;

import java.util.List;

/**
 * Created by rbentaarit on 9/19/2016.
 */
@Service
public class PromotionDetailServiceImpl implements IPromotionDetailService {

    private final IPromotionDetailDAO promotionDetailDAO;

    @Autowired
    public PromotionDetailServiceImpl(IPromotionDetailDAO promotionDetailDAO) {
        this.promotionDetailDAO = promotionDetailDAO;
    }

    @Override
    public PromotionDetail getPromotionDetailByPromotionId(Integer promotionId){
        PromotionDetail promotionDetail = promotionDetailDAO.getPromotionDetailByPromotionId(promotionId);
        if (promotionDetail != null) {
            return promotionDetail;
        }
        throw new PromotionDetailNotFoundException();
    }

    public List<PromoEffectivenessWhen> getPromoEffectivenessWhen(Integer offSet, Integer pageSize) {
        return promotionDetailDAO.getPromoEffectivenessWhen(offSet, pageSize);
    }
}

package us.deloitteinnovation.polaris.eventeval.service.internal;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.eventeval.dao.IEventEvalDAO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;
import us.deloitteinnovation.polaris.eventeval.service.IEventEvalService;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Service("eventEvalService")
public class EventEvalServiceImpl implements IEventEvalService {

    private static final Logger LOG = LoggerFactory.getLogger(EventEvalServiceImpl.class);

    private final IEventEvalDAO eventEvalDAO;

    /**
     * 
     * @param eventEvalDAO
     */
    @Autowired
    public EventEvalServiceImpl(IEventEvalDAO eventEvalDAO) {
        this.eventEvalDAO = eventEvalDAO;
    }


    @Override
    public String getEventOptimizerPromotionDetails(int productId,int customerId, int promotionId) {
        LOG.info("Entered : getEventOptimizerPromotionDetails---->>");
        return eventEvalDAO.getEventOptimizerPromotionDetails(productId, customerId, promotionId);
    }

    @Override
    public JSONObject saveEventOptimizerPromotionDetails(EventEvalPromotionVO eventEvalPromotionVO) {
        LOG.info("Entered : saveEventOptimizerPromotionDetails---->>");
        return eventEvalDAO.saveEventOptimizerPromotionDetails(eventEvalPromotionVO);
    }

    @Override
    public String getEventOptimizerSimulatedPromotionDetails(EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO) {
        LOG.info("Entered : getEventOptimizerSimulatedPromotionDetails---->>");
        return eventEvalDAO.getEventOptimizerSimulatedPromotionDetails(eventEvalSimulatedPromotionVO);
    }

}

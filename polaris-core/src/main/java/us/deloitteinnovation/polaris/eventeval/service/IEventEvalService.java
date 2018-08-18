package us.deloitteinnovation.polaris.eventeval.service;

import org.json.simple.JSONObject;

import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;

/**
 * 
 * @author Kranthi Velivala
 *
 */
public interface IEventEvalService {

	/**
	 * @param productId
	 * @param customerId
	 * @param promotionId
	 * @return String
	 */
    String getEventOptimizerPromotionDetails(int productId, int customerId, int promotionId);

    /**
     * 
     * @param eventEvalPromotionVO
     * @return JSONObject
     */
    JSONObject saveEventOptimizerPromotionDetails(EventEvalPromotionVO eventEvalPromotionVO);

    /**
     * 
     * @param eventEvalSimulatedPromotionVO
     * @return String
     */
    String getEventOptimizerSimulatedPromotionDetails(EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO);

}

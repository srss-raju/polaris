package us.deloitteinnovation.polaris.priceeval.service;

import org.json.simple.JSONObject;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;

import java.util.List;

/**
 * 
 * @author Kranthi Velivala
 *
 */
public interface IPriceEvalService {

	/**
	 * 
	 * @param customerId
	 * @param productLevel
	 * @param productLevelValue
	 * @param groupByValue
	 * @return JSONObject
	 */
    JSONObject getPriceEvaluatorDetails(int customerId, String productLevel, String productLevelValue, String groupByValue);

    /**
     * @param priceEvaluatorVOList
     * @return JSONObject
     */
    JSONObject savePriceEvaluatorDetails(List<PriceEvaluatorVO> priceEvaluatorVOList);

    /**
     * @return JSONObject
     */
    JSONObject getCustomers();

}

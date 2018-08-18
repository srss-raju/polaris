package us.deloitteinnovation.polaris.alerts.dao;

import org.json.simple.JSONObject;

/**
 * 
 * @author RajeshKumar B
 *
 */
public interface IAlertsDAO{

	/**
	 * 
	 * @return JSONObject
	 */
	JSONObject getAlertsDetail();

	/**
	 * 
	 * @param customerName
	 * @return JSONObject
	 */
    JSONObject getCustomerDetails(String customerName);

    /**
     * 
     * @param channelName
     * @return JSONObject
     */
    JSONObject getChannelDetails(String channelName);

}

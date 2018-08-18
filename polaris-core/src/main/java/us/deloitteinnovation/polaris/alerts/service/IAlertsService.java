package us.deloitteinnovation.polaris.alerts.service;

import org.json.simple.JSONObject;

/**
 * 
 * @author RajeshKumar B
 *
 */
public interface IAlertsService {

	/**
	 * @return JSONObject
	 */
    JSONObject getAlertsDetail();

    /**
     * @param customerName
     * @return JSONObject
     */
    JSONObject getCustomerDetails(String customerName);

    /**
     * @param channelName
     * @return JSONObject
     */
    JSONObject getChannelDetails(String channelName);
    
    String getTrustedTicket(String user, String targetSite);

}

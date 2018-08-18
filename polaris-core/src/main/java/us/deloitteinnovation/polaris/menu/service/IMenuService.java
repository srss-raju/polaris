package us.deloitteinnovation.polaris.menu.service;

import org.json.simple.JSONObject;


/**
 * 
 * @author RajeshKumar B
 *
 */
@FunctionalInterface
public interface IMenuService {

	/**
	 * @return JSONObject
	 */
	JSONObject getMenuDetails();
	
}

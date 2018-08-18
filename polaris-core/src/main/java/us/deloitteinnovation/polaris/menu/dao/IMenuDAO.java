package us.deloitteinnovation.polaris.menu.dao;

import org.json.simple.JSONObject;

/**
 * 
 * @author RajeshKumar B
 *
 */
@FunctionalInterface
public interface IMenuDAO {

	/**
	 * @return JSONObject
	 */
	JSONObject getMenuDetails();

}

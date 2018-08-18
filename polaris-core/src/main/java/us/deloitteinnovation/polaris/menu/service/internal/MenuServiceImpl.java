package us.deloitteinnovation.polaris.menu.service.internal;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.menu.dao.IMenuDAO;
import us.deloitteinnovation.polaris.menu.service.IMenuService;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	private IMenuDAO menuDAO;

    /**
     * 
     * @param menuDAO
     */
    @Autowired
    public MenuServiceImpl(IMenuDAO menuDAO) {
        this.menuDAO = menuDAO; 
    }
    
	/**
	 * @return JSONObject
	 */
	@Override
	public JSONObject getMenuDetails() {
		return menuDAO.getMenuDetails();
	}
}

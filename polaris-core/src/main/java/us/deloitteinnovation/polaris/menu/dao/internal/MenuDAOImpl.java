package us.deloitteinnovation.polaris.menu.dao.internal;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import us.deloitteinnovation.polaris.bookmarks.dao.internal.BookmarksDAOImpl;
import us.deloitteinnovation.polaris.menu.dao.IMenuDAO;
import us.deloitteinnovation.polaris.menu.util.ChartsMapper;
import us.deloitteinnovation.polaris.menu.util.MenuUtils;
import us.deloitteinnovation.polaris.common.util.Constant;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Repository
public class MenuDAOImpl implements IMenuDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(BookmarksDAOImpl.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MenuDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @return JSONObject
	 */
	@Override
	public JSONObject getMenuDetails() {
		LOG.debug("----getMenuDetails ---->> ");
		return MenuUtils.createMenu(jdbcTemplate.query(Constant.SQL_GET_MENU_DETAILS, new ChartsMapper()));
	}

}

/**
 * 
 */
package us.deloitteinnovation.polaris.menu.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import us.deloitteinnovation.polaris.menu.model.MenuVO;

/**
 * @author RajeshKumar B
 *
 */
public class ChartsMapper implements RowMapper<MenuVO> {

	/**
	 * @return MenuVO
	 */
	@Override
	public MenuVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuVO menuVO = new MenuVO();
		menuVO.setModule(rs.getString("Module"));
		menuVO.setMainQuestion(rs.getString("Main_Question"));
		menuVO.setMainQuestionIndex(rs.getInt("Main_Question_Index"));
		menuVO.setSubQuestion(rs.getString("Sub_Question"));
		menuVO.setSubQuestionIndex(rs.getInt("Sub_Question_Index"));
		menuVO.setUrl(rs.getString("URL"));
		return menuVO;
	}
}

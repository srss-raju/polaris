package us.deloitteinnovation.polaris.menu.util;

import java.util.List;

import org.json.simple.JSONObject;

import us.deloitteinnovation.polaris.menu.model.MenuVO;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class MenuUtils {
	
	private MenuUtils() {
	}

	/**
	 * @param menuVOList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject createMenu(List<MenuVO> menuVOList) {

		JSONObject mainJson = new JSONObject();
		String previousModuleName = null;
		JSONObject moduleJson = null;
		boolean flag = false;
		String previousMainQuestion = null;
		JSONObject mainQuestionJson = null;

		for (MenuVO menuVO : menuVOList) {
			if (!menuVO.getModule().equals(previousModuleName)) {
				previousModuleName = menuVO.getModule();
				flag = true;
				moduleJson = new JSONObject();
			}
			if (!menuVO.getMainQuestion().equals(previousMainQuestion)) {
				mainQuestionJson = new JSONObject();
				mainQuestionJson.put(menuVO.getSubQuestion(), menuVO.getUrl());

			} else {
				mainQuestionJson.put(menuVO.getSubQuestion(), menuVO.getUrl());
			}
			moduleJson.put(menuVO.getMainQuestion(), mainQuestionJson);

			previousMainQuestion = menuVO.getMainQuestion();
			if (flag) {
				mainJson.put(previousModuleName, moduleJson);
			}
			flag = false;
		}

		return mainJson;
	}

}

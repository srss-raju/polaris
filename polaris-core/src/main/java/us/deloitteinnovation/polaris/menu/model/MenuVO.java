package us.deloitteinnovation.polaris.menu.model;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class MenuVO {
	
	private String module;
	private String mainQuestion;
	private int mainQuestionIndex;
	private String subQuestion;
	private int subQuestionIndex;
	
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMainQuestion() {
		return mainQuestion;
	}
	public void setMainQuestion(String mainQuestion) {
		this.mainQuestion = mainQuestion;
	}
	public int getMainQuestionIndex() {
		return mainQuestionIndex;
	}
	public void setMainQuestionIndex(int mainQuestionIndex) {
		this.mainQuestionIndex = mainQuestionIndex;
	}
	public String getSubQuestion() {
		return subQuestion;
	}
	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}
	public int getSubQuestionIndex() {
		return subQuestionIndex;
	}
	public void setSubQuestionIndex(int subQuestionIndex) {
		this.subQuestionIndex = subQuestionIndex;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String url;
	
	

}

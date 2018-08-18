package us.deloitteinnovation.polaris.workflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbentaarit on 8/4/2016.
 */


public class Story {
    public static final int STORY_MAX_LENGTH = 150;

    private int id;
    private int moduleId;
    private String title;
    private Short sortIndex;
    private Boolean isDisabled;
    private List<Question>  questionList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Short sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}

package us.deloitteinnovation.polaris.workflow.model;

import javax.validation.constraints.Min;

/**
 * Created by rbentaarit on 8/4/2016.
 */

public class Question {


    private int id;
    private int storyId;
    private String title;
    private Short sortIndex;
    private Boolean isDisabled;
    @Min(value = 1)
    private Long bookmarkId;
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
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

    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

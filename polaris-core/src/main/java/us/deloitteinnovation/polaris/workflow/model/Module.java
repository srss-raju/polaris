package us.deloitteinnovation.polaris.workflow.model;


import us.deloitteinnovation.polaris.role.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbentaarit on 8/4/2016.
 */

public class Module {

    private int id;
    private String title;
    private Short sortIndex;
    private Boolean IsDisabled = Boolean.FALSE;
    private Boolean IsDefault = Boolean.FALSE;
    private List<Role> roles;

    List<Story> storyList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return IsDisabled;
    }

    public void setDisabled(Boolean disabled) {
        IsDisabled = disabled;
    }

    public Boolean getDefault() {
        return IsDefault;
    }

    public void setDefault(Boolean aDefault) {
        IsDefault = aDefault;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }
}

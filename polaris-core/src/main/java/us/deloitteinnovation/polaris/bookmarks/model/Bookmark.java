package us.deloitteinnovation.polaris.bookmarks.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author RajeshKumar B
 */
public class Bookmark {

    private BigInteger bookmarksID;
    private String linkName;
    private String state;
    private String chartName;
    private String userID;
    private Timestamp timeStamp;
    private String companyName;
    private boolean privateFlag;
    private String filter;
    private String config;

    /**
     * @return the bookmarksID
     */
    public BigInteger getBookmarksID() {
        return bookmarksID;
    }

    /**
     * @param bookmarksID
     *     the bookmarksID to set
     */
    public void setBookmarksID(BigInteger bookmarksID) {
        this.bookmarksID = bookmarksID;
    }

    /**
     * @return the linkName
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * @param linkName
     *     the linkName to set
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *     the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the chartName
     */
    public String getChartName() {
        return chartName;
    }

    /**
     * @param chartName
     *     the chartName to set
     */
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID
     *     the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp != null ? (Timestamp) timeStamp.clone() : null;
    }

    /**
     * @param timeStamp
     *     the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp != null ? new Timestamp(timeStamp.getTime()) : null;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *     the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return privateFlag
     */
    public boolean isPrivateFlag() {
        return privateFlag;
    }

    /**
     * @param privateFlag
     */
    public void setPrivateFlag(boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "bookmarksID=" + bookmarksID +
                ", linkName='" + linkName + '\'' +
                ", state='" + state + '\'' +
                ", chartName='" + chartName + '\'' +
                ", userID='" + userID + '\'' +
                ", timeStamp=" + timeStamp +
                ", companyName='" + companyName + '\'' +
                ", privateFlag=" + privateFlag +
                '}';
    }
}

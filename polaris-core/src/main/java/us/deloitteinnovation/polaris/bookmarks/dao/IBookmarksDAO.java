package us.deloitteinnovation.polaris.bookmarks.dao;

import org.json.simple.JSONObject;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;

import java.util.List;

/**
 * @author RajeshKumar B
 */
public interface IBookmarksDAO {

    /**
     * creates the bookmark
     *
     * @param bookmark contains the bookmark details
     * @return the saved bookmark with Id
     */
    Bookmark saveUserBookmark(Bookmark bookmark);

    /**
     * Gets the bookmarks based on the UserName and the flag
     *
     * @param userName    contains the user name
     * @param privateFlag contains the type (private(true)/public(false)) of bookmark
     * @return the List<Bookmark> based on the criteria
     */
    List<Bookmark> getUserBookmarks(String userName, boolean privateFlag);

    /**
     * Fetch the bookmark based on the bookmarkId
     *
     * @param bookmarkId is the unique Id for a bookmark
     * @return the bookmark based on the input bookmarkId
     */
    Bookmark getUserBookmark(Long bookmarkId);

    /**
     * Deletes the bookmarks based on the input ids
     *
     * @param deleteUserBookmarksList contains the list of Ids
     * @return the status with deleted bookmark count
     */
    JSONObject deleteUserBookmarks(List<Long> deleteUserBookmarksList);

    /**
     * updates the bookmark
     *
     * @param bookmark contains the bookmark details
     * @return the updated bookmark
     */
    Bookmark updateBookmark(Bookmark bookmark);

    /**
     * Checks the bookmark uniqueness based on the bookmark name and the type (private/public)
     *
     * @param bookmark contains the name , private flag and other data
     * @return status of the bookmark uniqueness
     * </br>
     * true - bookmark contains unique name
     * false - bookmark contains the duplicate name
     */
    Boolean isBookmarkLinkNameUnique(Bookmark bookmark);
}

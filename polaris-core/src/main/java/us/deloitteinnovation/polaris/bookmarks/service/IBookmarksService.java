package us.deloitteinnovation.polaris.bookmarks.service;

import org.json.simple.JSONObject;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;

import java.util.List;

public interface IBookmarksService {

    /**
     * creates the bookmark
     *
     * @param bookmark contains the bookmark details
     * @return the saved bookmark with Id
     */
    Bookmark saveUserBookmarks(Bookmark bookmark);

    /**
     * Gets the bookmarks based on the user name and private flag
     *
     * @param userName    contains the user name
     * @param privateFlag contains the type of bookmark
     * @return the list of bookmarks based on the criteria
     */
    List<Bookmark> getUserBookmarks(String userName, boolean privateFlag);

    /**
     * @param bookmarkId contains the bookmark unique id
     * @return the bookmark
     */
    Bookmark getUserBookmark(Long bookmarkId);

    /**
     * Deletes the requested bookmarks
     *
     * @param deleteUserBookmarksList contains the list of bookmarks ids
     * @return the deleted bookmark count
     */
    JSONObject deleteUserBookmarks(List<Long> deleteUserBookmarksList);

    /**
     * updates the bookmark
     *
     * @param bookmark contains the updated bookmark details
     * @return the updated bookmark
     */
    Bookmark updateUserBookmark(Bookmark bookmark);

}

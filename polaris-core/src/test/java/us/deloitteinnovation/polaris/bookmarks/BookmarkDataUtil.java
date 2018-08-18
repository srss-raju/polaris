package us.deloitteinnovation.polaris.bookmarks;

import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaddepalli on 15-03-2017.
 */
public class BookmarkDataUtil {


    public static final String USER1_ID = "user1";
    public static final String USER2_ID = "user2";

    public static final Boolean PRIVATE = Boolean.TRUE;
    public static final Boolean PUBLIC = Boolean.FALSE;

    public static Bookmark getBookmark(String bookmarkId, String linkName, String userId, boolean privateFlag) {
        Bookmark bookmark = new Bookmark();
        if (null != bookmarkId) {
            bookmark.setBookmarksID(new BigInteger(bookmarkId));
        }
        bookmark.setLinkName(linkName);
        bookmark.setState("test state");
        bookmark.setChartName("Price Ladders");
        bookmark.setPrivateFlag(privateFlag);
        bookmark.setUserID(userId);
        bookmark.setCompanyName("Deloitte");
        return bookmark;
    }

    public static List<Bookmark> getBookmarkList(int bookmarkCount) {
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (int i = 0; i < bookmarkCount; i++) {
            bookmarkList.add(BookmarkDataUtil.getBookmark(null, "link name " + i, "polarisdev", Boolean.TRUE));
        }
        return bookmarkList;
    }


}

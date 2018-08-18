package us.deloitteinnovation.polaris.bookmarks;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.bookmarks.dao.internal.BookmarksDAOImpl;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.common.AbstractTest;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookmarksDAOTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(BookmarksDAOTest.class);

    private BookmarksDAOImpl bookmarksDAO;


    @Before
    public void setup() {
        this.bookmarksDAO = new BookmarksDAOImpl();
        this.bookmarksDAO.setDatasource(dataSource);
    }

    /****************************************************************************************************************
     *********************************  Test cases for the bookmark creation ****************************************
     ****************************************************************************************************************/

    @Test
    public void testSavePrivateBookmark() throws SQLException {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "Quarter Filter", "polarisdev", true);
        Bookmark result = bookmarksDAO.saveUserBookmark(bookmark);
        Assert.assertNotNull(bookmark.getBookmarksID());
        LOG.info("Result ----->> ", result.toString());
    }

    @Test
    public void testSavePublicBookmark() throws SQLException {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "Quarter Filter", "polarisdev", false);
        Bookmark result = bookmarksDAO.saveUserBookmark(bookmark);
        Assert.assertNotNull(bookmark.getBookmarksID());
        LOG.info("Result ----->> ", result);
    }

    /****************************************************************************************************************
     *********************************  Test cases for the bookmark update ******************************************
     ****************************************************************************************************************/
    @Test
    public void testUpdateBookmark() throws SQLException {
        Bookmark bookmark = BookmarkDataUtil.getBookmark("1", "Quarter Filter update", "polarisdev", false);
        bookmark.setTimeStamp(new Timestamp(new Date().getTime()));
        Bookmark result = bookmarksDAO.updateBookmark(bookmark);
        Assert.assertNotNull(bookmark.getBookmarksID());
        LOG.info("Result ----->> ", result);
    }

    /****************************************************************************************************************
     *********************************  Test cases for the bookmarks fetching ****************************************
     ****************************************************************************************************************/

    @Test
    public void testGetBookmarks() {
        List<Bookmark> bookmarkList = bookmarksDAO.getUserBookmarks("user1", false);
        Assert.assertNotNull(bookmarkList);
        Assert.assertTrue(bookmarkList.size() > 0);
    }

    @Test
    public void testGetBookmark() {
        Bookmark bookmark = bookmarksDAO.getUserBookmark(2L);
        Assert.assertNotNull(bookmark);
    }


    @Test
    public void tesGetUserBookmark() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "Quarter Filter", "polarisdev", true);
        Bookmark saveResult = bookmarksDAO.saveUserBookmark(bookmark);
        // validating bookmark creation
        Assert.assertNotNull(saveResult);
        Bookmark fetchedBookmark = bookmarksDAO.getUserBookmark((saveResult.getBookmarksID()).longValue());
        // validating the fetched bookmark with original bookmark
        Assert.assertEquals(fetchedBookmark.getChartName(), bookmark.getChartName());
    }


    /****************************************************************************************************************
     ***************************************  Test cases for the bookmark deletion **********************************
     ****************************************************************************************************************/

    @Test
    public void testDeleteUserBookmarks() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "Quarter Filter", "polarisdev", true);
        Bookmark saveResult = bookmarksDAO.saveUserBookmark(bookmark);
        // validating bookmark creation
        Assert.assertNotNull(saveResult);

        List<Long> deleteUserBookmarksList = new ArrayList<>();
        deleteUserBookmarksList.add(saveResult.getBookmarksID().longValue());
        JSONObject result = bookmarksDAO.deleteUserBookmarks(deleteUserBookmarksList);
        Assert.assertEquals(result.get("success"), "1 records deleted");
    }

    @Test
    public void testDeleteUserBookmarksException() {
        List<Long> deleteUserBookmarksList = new ArrayList<Long>();
        deleteUserBookmarksList.add(1L);
        JSONObject result = bookmarksDAO.deleteUserBookmarks(deleteUserBookmarksList);
        LOG.info("result --->> " + result);
    }

    /****************************************************************************************************************
     **************************  Test cases for the unique bookmark creation validation  ****************************
     ****************************************************************************************************************/
    /*
        Existing data as below
        -------------------------------------------
        User            LinkName            private
        -------------------------------------------
        user1           book1                   0
        user1           book2                   0
        user1           book3                   1
        user1           book4                   1
        user2           book5                   0
        user2           book6                   0
        user2           book7                   1
        user2           book8                   1
    */
    // Direct scenario of checking a new bookmark name as public - pass
    @Test
    public void testCheckUniqueBookmarkName1() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book12345", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertTrue("Unique public bookmark created", status);
    }

    // Direct scenario of checking a new bookmark name as private - pass
    @Test
    public void testCheckUniqueBookmarkName2() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book12345", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertTrue("Unique private bookmark created", status);
    }

    // check for creating a private bookmark name which already exists as public in same account - failed
    @Test
    public void testCheckUniqueBookmarkName3() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book1", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertFalse(status);
    }

    // check for creating a private bookmark name which already exists as public in different account - failed
    @Test
    public void testCheckUniqueBookmarkName4() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book5", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertFalse(status);
    }

    // check for creating a private bookmark name which already exists as private in same account - failed
    @Test
    public void testCheckUniqueBookmarkName5() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book3", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertFalse(status);
    }

    // check for creating a private bookmark name which already exists as private in different account - pass
    @Test
    public void testCheckUniqueBookmarkName6() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book7", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertTrue(status);
    }

    // check for creating a public bookmark name which already exists as public in same account - failed
    @Test
    public void testCheckUniqueBookmarkName7() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book2", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertFalse(status);
    }

    // check for creating a public bookmark name which already exists as public in different account - failed
    @Test
    public void testCheckUniqueBookmarkName8() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book6", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertFalse(status);
    }

    // check for creating a public bookmark name which already exists as private in same account - failed
    @Test
    public void testCheckUniqueBookmarkName9() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book3", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertFalse(status);
    }

    // check for creating a public bookmark name which already exists as private in different account - failed
    @Test
    public void testCheckUniqueBookmarkName10() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark(null, "book7", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertFalse(status);
    }

    /*Test cases for the bookmark creation update*/

    // check for updating the same bookmark name with case change - pass
    @Test
    public void testCheckUniqueBookmarkName11() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark("15", "Book1", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertTrue(status);
    }

    // check for updating the private bookmark to public + bookmark name exists as private in different account - fail
    @Test
    public void testCheckUniqueBookmarkName12() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark("922", "book6", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertFalse(status);
    }

    // check for updating the private bookmark to public + name not exists in other account - pass
    @Test
    public void testCheckUniqueBookmarkName13() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark("922", "book420", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PUBLIC));
        Assert.assertTrue(status);
    }

    // check for updating the public bookmark to private + name exists as public in same account (scenario not exists if properly saved)

    // check for updating the public bookmark to private + name exists as private in same account or different account (scenario not exists if properly saved)

    // check for updating the public bookmark to private + bookmark not exists any where other than this - pass
    @Test
    public void testCheckUniqueBookmarkName14() {
        Boolean status = bookmarksDAO.isBookmarkLinkNameUnique(BookmarkDataUtil.getBookmark("16", "book2", BookmarkDataUtil.USER1_ID, BookmarkDataUtil.PRIVATE));
        Assert.assertTrue(status);
    }

}

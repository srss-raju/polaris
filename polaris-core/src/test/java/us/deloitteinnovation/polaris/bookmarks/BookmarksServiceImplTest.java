package us.deloitteinnovation.polaris.bookmarks;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.bookmarks.dao.internal.BookmarksDAOImpl;
import us.deloitteinnovation.polaris.bookmarks.exception.DuplicateNameBookmarkException;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.bookmarks.service.IBookmarksService;
import us.deloitteinnovation.polaris.bookmarks.service.internal.BookmarksServiceImpl;
import us.deloitteinnovation.polaris.common.AbstractTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;

@Component
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookmarksServiceImplTest extends AbstractTest {

    @Mock
    private BookmarksDAOImpl bookmarksDAO;

    private IBookmarksService bookmarksService;

    @Before
    public void setupService() {
        bookmarksService = new BookmarksServiceImpl(bookmarksDAO);
    }

    /**************************************************************************************************************************************************************************
     *********************************************************************  Test cases for saveUserBookmarks  *****************************************************************
     *************************************************************************************************************************************************************************/

    @Test
    public void testSaveUserBookmarks() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(bookmarksDAO.saveUserBookmark(bookmark)).thenReturn(bookmark);
        Mockito.when(bookmarksDAO.isBookmarkLinkNameUnique(bookmark)).thenReturn(Boolean.TRUE);
        Assert.assertEquals(bookmark, bookmarksService.saveUserBookmarks(bookmark));
    }

    @Test(expected = DuplicateNameBookmarkException.class)
    public void testSaveUserBookmarksDuplicateNameException() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(bookmarksDAO.saveUserBookmark(bookmark)).thenReturn(bookmark);
        Mockito.when(bookmarksDAO.isBookmarkLinkNameUnique(bookmark)).thenReturn(Boolean.FALSE);
        Assert.assertEquals(bookmark, bookmarksService.saveUserBookmarks(bookmark));
    }

    /**************************************************************************************************************************************************************************
     *********************************************************************  Test cases for getUserBookmarks  ******************************************************************
     *************************************************************************************************************************************************************************/

    @Test
    public void testGetUserBookmarks() {
        List<Bookmark> bookmarkList = BookmarkDataUtil.getBookmarkList(5);
        Mockito.when(bookmarksDAO.getUserBookmarks(anyString(), anyBoolean())).thenReturn(bookmarkList);
        Assert.assertEquals(bookmarkList, bookmarksService.getUserBookmarks(anyString(), anyBoolean()));
    }


    /**************************************************************************************************************************************************************************
     *********************************************************************  Test cases for getUserBookmark  *******************************************************************
     *************************************************************************************************************************************************************************/
    @Test
    public void testGetUserBookmark() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark(null, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(bookmarksDAO.getUserBookmark(anyLong())).thenReturn(bookmark);
        Assert.assertEquals(bookmark, bookmarksService.getUserBookmark(anyLong()));
    }

    /**************************************************************************************************************************************************************************
     *********************************************************************  Test cases for deleteUserBookmarks  ***************************************************************
     *************************************************************************************************************************************************************************/

    @Test
    public void testDeleteUserBookmarks() {
        List<Long> deleteUserBookmarksList = new ArrayList<>();
        deleteUserBookmarksList.add(47L);
        Mockito.when(bookmarksDAO.deleteUserBookmarks(deleteUserBookmarksList)).thenReturn(new JSONObject());
        Assert.assertEquals(new JSONObject(), bookmarksService.deleteUserBookmarks(deleteUserBookmarksList));
    }

    /**************************************************************************************************************************************************************************
     *********************************************************************  Test cases for updateUserBookmark  ****************************************************************
     *************************************************************************************************************************************************************************/

    @Test
    public void testUpdateUserBookmark() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark("5", "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(bookmarksDAO.updateBookmark(bookmark)).thenReturn(bookmark);
        Mockito.when(bookmarksDAO.isBookmarkLinkNameUnique(bookmark)).thenReturn(Boolean.TRUE);
        Assert.assertEquals(bookmark, bookmarksService.updateUserBookmark(bookmark));
    }

    @Test(expected = DuplicateNameBookmarkException.class)
    public void testUpdateUserBookmarkDuplicateNameException() {
        Bookmark bookmark = BookmarkDataUtil.getBookmark("5", "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(bookmarksDAO.updateBookmark(bookmark)).thenReturn(bookmark);
        Mockito.when(bookmarksDAO.isBookmarkLinkNameUnique(bookmark)).thenReturn(Boolean.FALSE);
        Assert.assertEquals(bookmark, bookmarksService.updateUserBookmark(bookmark));
    }

}

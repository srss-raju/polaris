package us.deloitteinnovation.polaris.bookmarks.service.internal;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.bookmarks.dao.IBookmarksDAO;
import us.deloitteinnovation.polaris.bookmarks.exception.DuplicateNameBookmarkException;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.bookmarks.service.IBookmarksService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author RajeshKumar B
 */
@Service("bookmarksService")
public class BookmarksServiceImpl implements IBookmarksService {

    private static final Logger LOG = LoggerFactory.getLogger(BookmarksServiceImpl.class);

    private IBookmarksDAO bookmarksDAO;

    @Autowired
    public BookmarksServiceImpl(IBookmarksDAO bookmarksDAO) {
        this.bookmarksDAO = bookmarksDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Bookmark saveUserBookmarks(Bookmark bookmark) {
        if (!bookmarksDAO.isBookmarkLinkNameUnique(bookmark)) {
            LOG.debug("LinkName: \"" + bookmark.getLinkName() + "\" already exists!");
            throw new DuplicateNameBookmarkException();
        }
        return bookmarksDAO.saveUserBookmark(bookmark);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bookmark> getUserBookmarks(String userName, boolean privateFlag) {
        return bookmarksDAO.getUserBookmarks(userName, privateFlag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bookmark getUserBookmark(Long bookmarkId) {
        return bookmarksDAO.getUserBookmark(bookmarkId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject deleteUserBookmarks(List<Long> deleteUserBookmarksList) {
        return bookmarksDAO.deleteUserBookmarks(deleteUserBookmarksList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Bookmark updateUserBookmark(Bookmark bookmark) {
        if (!bookmarksDAO.isBookmarkLinkNameUnique(bookmark)) {
            LOG.debug("LinkName: \"" + bookmark.getLinkName() + "\" already exists!");
            throw new DuplicateNameBookmarkException();
        }
        return bookmarksDAO.updateBookmark(bookmark);
    }

}

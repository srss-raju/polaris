package us.deloitteinnovation.polaris.bookmarks.dao.internal;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.bookmarks.dao.IBookmarksDAO;
import us.deloitteinnovation.polaris.bookmarks.exception.*;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.common.util.Constant;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author RajeshKumar B
 */
@Repository
public class BookmarksDAOImpl extends AbstractDAO implements IBookmarksDAO {

    private static final Logger LOG = LoggerFactory.getLogger(BookmarksDAOImpl.class);

    public static final String SQL_GET_BOOKMARKS = "SELECT [BOOKMARK_ID],[LINKNAME],[STATE] , [CHARTNAME],[USER_ID],[TIMESTAMP],[PRIVATE], [FILTER], [CONFIG] FROM [app_Bookmarks] WHERE [PRIVATE] = 0 ";
    public static final String SQL_PRIVATE_FLAG_FILTER = "OR ([USER_ID] = :user AND [PRIVATE]=1)";

    private static final String SQL_GET_BOOKMARK = "SELECT [BOOKMARK_ID],[LINKNAME],[STATE],[CHARTNAME],[USER_ID],[TIMESTAMP],[PRIVATE],[FILTER], [CONFIG] FROM [app_Bookmarks] WHERE [BOOKMARK_ID] =:bookmarkId";
    private static final String UPDATE_BOOKMARK = "UPDATE [app_Bookmarks] SET LINKNAME=:LINKNAME, PRIVATE=:PRIVATE, TIMESTAMP=GETDATE(), FILTER=:FILTER, CONFIG=:CONFIG WHERE BOOKMARK_ID=:bookmarkId ";

    /**
     * Checking the bookmark by name and then we have conditions based on two scenarios
     * <br> Create bookmark scenario <br>
     * While creating a new bookmark as
     * <b>public</b> then we need to check whether any bookmark exists irrespective whether it is private or public
     * <b>private</b> then we need to check any public bookmark exists or any private bookmark exists under his account
     * <br> Update bookmark scenario <br>
     * first we need to skip the current record in checking
     * <b>changing public to private</b> then we need to check whether any bookmark exists as private or public in his account (skipping the current record)
     * <b>changing private to public</b> then we need to check any bookmark exists irrespective whether it is public or private (skipping the current record)
     * <br>
     * if exists then the query will return 0(false)
     * otherwise 1(true)
     */
    private static final String IS_LINKNAME_EXIST = "SELECT CASE WHEN Count ([LINKNAME]) > 0 THEN 0 WHEN count ([LINKNAME]) = 0 THEN 1 END FROM [dbo].[app_Bookmarks] WHERE [LINKNAME] LIKE :linkName AND [BOOKMARK_ID] != :bookmarkId AND ([PRIVATE] =0 OR ([PRIVATE] = 1 AND [USER_ID] = :userId) OR ([PRIVATE] = 1 AND 0 = :isPrivate ))";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Bookmarks");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("BOOKMARK_ID");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bookmark saveUserBookmark(final Bookmark bookmark) {
        Map<String, Object> params = buildBookmarkParams(bookmark);
        Number bookmarkId;
        try {
            bookmarkId = simpleJdbcInsert.executeAndReturnKey(params);
        } catch (Exception e) {
            throw new BookmarkCreationException(e);
        }
        LOG.debug("----BookMark ID Generated IS ---->> {} ", bookmarkId);
        bookmark.setBookmarksID(BigInteger.valueOf(bookmarkId.longValue()));
        return bookmark;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bookmark> getUserBookmarks(String userName, boolean privateFlag) {
        try {
            return getNamedParameterJdbcTemplate().query(privateFlag ? SQL_GET_BOOKMARKS + SQL_PRIVATE_FLAG_FILTER : SQL_GET_BOOKMARKS,
                    Collections.singletonMap("user", userName), new BookmarksMapper());
        } catch (Exception e) {
            throw new BookmarkMultiFetchException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bookmark getUserBookmark(Long bookmarkId) {
        if (null == bookmarkId) {
            throw new InvalidBookmarkIdException();
        }
        try {
            return getNamedParameterJdbcTemplate().queryForObject(SQL_GET_BOOKMARK, Collections.singletonMap("bookmarkId",bookmarkId),new BookmarksMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new BookmarkNotFoundException(e);
        } catch (Exception e) {
            throw new BookmarkFetchException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject deleteUserBookmarks(List<Long> deleteUserBookmarksList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("deleteUserBookmarks", deleteUserBookmarksList);
        int deletedRecordsCount;
        try {
            deletedRecordsCount = getNamedParameterJdbcTemplate().update(Constant.SQL_DELETE_BOOKMARK, parameters);
        } catch (Exception e) {
            throw new BookmarkDeletionException(e);
        }

        JSONObject bookmarksResult = new JSONObject();
        if (deletedRecordsCount > 0) {
            bookmarksResult.put("success", deletedRecordsCount + " records deleted");
        } else {
            bookmarksResult.put("success", "no records to delete");
        }
        LOG.debug("Deleted {} records ", deletedRecordsCount);
        return bookmarksResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bookmark updateBookmark(Bookmark bookmark) {
        if (null == bookmark || null == bookmark.getBookmarksID()) {
            throw new InvalidBookmarkIdException();
        }
        Map<String, Object> params = buildBookmarkParams(bookmark);
        try {
            getNamedParameterJdbcTemplate().update(UPDATE_BOOKMARK, params);
        } catch (Exception e) {
            throw new BookmarkUpdateException(e);
        }
        return bookmark;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isBookmarkLinkNameUnique(Bookmark bookmark) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("linkName", bookmark.getLinkName());
        params.put("bookmarkId", null != bookmark.getBookmarksID() ? bookmark.getBookmarksID().intValue() : 0);
        params.put("userId", bookmark.getUserID());
        params.put("isPrivate", bookmark.isPrivateFlag() ? 1 : 0);
        LOG.debug("Query params for the unique bookmark validation {}", params);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(IS_LINKNAME_EXIST, params, Boolean.class);
        } catch (Exception e) {
            throw new BookmarkUniqueCheckException(e);
        }
    }

    /*utils specific to the bookmark*/

    /**
     * Util for building the bookmark map for creating or updating
     *
     * @param bookmark
     * @return Map<String, Object>
     */
    private static Map<String, Object> buildBookmarkParams(Bookmark bookmark) {
        Map<String, Object> params = new HashMap<>(6);
        if (null != bookmark.getBookmarksID()) {
            params.put("bookmarkId", bookmark.getBookmarksID().intValue());
        } else {
            params.put("TIMESTAMP", DateTime.now().toDate());
        }
        params.put("LINKNAME", bookmark.getLinkName());
        params.put("STATE", bookmark.getState());
        params.put("CHARTNAME", bookmark.getChartName());
        params.put("USER_ID", bookmark.getUserID());
        params.put("PRIVATE", bookmark.isPrivateFlag());
        params.put("FILTER", bookmark.getFilter());
        params.put("CONFIG", bookmark.getConfig());
        return params;
    }

    private class BookmarksMapper implements RowMapper<Bookmark> {

        @Override
        public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bookmark bookmark = new Bookmark();
            bookmark.setBookmarksID(BigInteger.valueOf(rs.getInt("BOOKMARK_ID")));
            bookmark.setLinkName(rs.getString("LINKNAME"));
            bookmark.setChartName(rs.getString("CHARTNAME"));
            bookmark.setUserID(rs.getString("USER_ID"));
            bookmark.setPrivateFlag(rs.getBoolean("PRIVATE"));
            bookmark.setState(rs.getString("STATE"));
            bookmark.setFilter(rs.getString("FILTER"));
            bookmark.setConfig(rs.getString("CONFIG"));
            return bookmark;
        }
    }

}

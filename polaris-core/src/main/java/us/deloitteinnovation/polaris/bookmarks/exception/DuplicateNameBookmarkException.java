package us.deloitteinnovation.polaris.bookmarks.exception;

/**
 * Created by rbentaarit on 3/14/2017.
 */
public class DuplicateNameBookmarkException extends BookmarkException {

    public DuplicateNameBookmarkException(String message) {
        super(message);
    }

    public DuplicateNameBookmarkException() {
        LOG.info("Actually a duplicate bookmark");
    }
}

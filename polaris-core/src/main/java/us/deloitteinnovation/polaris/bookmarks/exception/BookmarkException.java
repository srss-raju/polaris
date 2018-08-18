package us.deloitteinnovation.polaris.bookmarks.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rbentaarit on 3/14/2017.
 */
public class BookmarkException extends RuntimeException {

    public static final Logger LOG = LoggerFactory.getLogger(BookmarkException.class);

    public BookmarkException() {
    }

    public BookmarkException(String message) {
        super(message);
    }

    public BookmarkException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookmarkException(Throwable cause) {
        super(cause);
        LOG.error(cause.getMessage());
    }

    public BookmarkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

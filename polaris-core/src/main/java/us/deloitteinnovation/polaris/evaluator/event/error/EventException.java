package us.deloitteinnovation.polaris.evaluator.event.error;

import org.slf4j.LoggerFactory;
import us.deloitteinnovation.polaris.evaluator.event.dao.internal.EventDAOImpl;

/**
 * Created by rbentaarit on 6/14/2017.
 */
public abstract class EventException extends RuntimeException {
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EventDAOImpl.class);
    public EventException() {
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

}

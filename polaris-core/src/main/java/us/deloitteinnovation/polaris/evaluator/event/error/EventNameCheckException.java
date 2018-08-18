package us.deloitteinnovation.polaris.evaluator.event.error;

/**
 * Created by hkaja on 12-07-2017.
 */
public class EventNameCheckException extends EventException {
    public EventNameCheckException() {
    }
    public EventNameCheckException(String message) {
        super(message);
        LOG.info(message);
    }

    public EventNameCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
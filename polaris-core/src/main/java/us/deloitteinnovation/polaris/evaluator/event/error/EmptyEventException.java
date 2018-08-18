package us.deloitteinnovation.polaris.evaluator.event.error;

/**
 * Created by rbentaarit on 6/14/2017.
 */
public class EmptyEventException extends EventException {
    public EmptyEventException() {
    }
    public EmptyEventException(String message) {
        super(message);
        LOG.info(message);
    }

    public EmptyEventException(String message, Throwable cause) {
        super(message, cause);
    }
}

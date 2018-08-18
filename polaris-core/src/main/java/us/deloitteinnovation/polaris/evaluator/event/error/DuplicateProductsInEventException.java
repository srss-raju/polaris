package us.deloitteinnovation.polaris.evaluator.event.error;

/**
 * Created by hkaja on 12-07-2017.
 */
public class DuplicateProductsInEventException extends EventException {
    public DuplicateProductsInEventException() {
    }
    public DuplicateProductsInEventException(String message) {
        super(message);
        LOG.info(message);
    }

    public DuplicateProductsInEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
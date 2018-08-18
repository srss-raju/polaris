package us.deloitteinnovation.polaris.evaluator.scenario.error;

import org.slf4j.LoggerFactory;
import us.deloitteinnovation.polaris.evaluator.event.dao.internal.EventDAOImpl;

/**
 * Created by hkaja on 07/07/2017.
 */
public class ScenarioException extends RuntimeException {
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EventDAOImpl.class);
    public ScenarioException() {
    }

    public ScenarioException(String message) {
        super(message);
    }

    public ScenarioException(String message, Throwable cause) {
        super(message, cause);
    }

}

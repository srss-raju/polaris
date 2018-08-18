package us.deloitteinnovation.polaris.alerts.exception;

/**
 * Base User Exception
 *
 * @author RajeshKumar B
 * @since 7/2/2016
 */
public class AuthenticationException extends Exception {

    private static final long serialVersionUID = -8781709582156224413L;

    /**
     * 
     * @param s
     */
    public AuthenticationException(Exception s) {
        super(s);
    }
}

package us.deloitteinnovation.polaris.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by rbentaarit on 6/14/2017.
 */

@Component("userAuditor")
public class UserAuditorAware implements AuditorAware<String> {

    private static final String SYSTEM_AUDITOR = "SYSTEM";

    @Override
    public String getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof SessionVO) {
                    return ((SessionVO) principal).getEmail();
                }
            }
        }
        // Default back to the system auditor
        return SYSTEM_AUDITOR;
    }
}

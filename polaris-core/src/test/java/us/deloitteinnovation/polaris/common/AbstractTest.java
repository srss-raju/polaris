package us.deloitteinnovation.polaris.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import us.deloitteinnovation.polaris.DatabaseConfiguration;
import us.deloitteinnovation.polaris.EngineConfiguration;
import us.deloitteinnovation.polaris.security.SessionVO;

import javax.sql.DataSource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EngineConfiguration.class, DatabaseConfiguration.class})
public abstract class AbstractTest {

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setup() {
        SessionVO sessionVO = new SessionVO();
        sessionVO.setEmail("test@mail.com");

        Authentication authentication = new UsernamePasswordAuthenticationToken(sessionVO, null);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // TODO: Add any global setup here
        MockitoAnnotations.initMocks(this);
    }
}

package us.deloitteinnovation.polaris;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Test-Specific Configurations
 *
 * @author ltornquist
 * @since 7/1/2015
 */
@Configuration
@PropertySource("classpath:application-test.properties")
@Import(EngineConfiguration.class)
@EnableAutoConfiguration
public class EngineTestConfiguration {
}

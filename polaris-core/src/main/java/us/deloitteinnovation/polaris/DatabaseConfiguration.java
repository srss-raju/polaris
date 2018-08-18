package us.deloitteinnovation.polaris;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import us.deloitteinnovation.polaris.security.SessionVO;
import us.deloitteinnovation.polaris.common.util.Constant;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

/**
 * POLARIS Engine Database Configuration
 *
 * @author RajeshKumar B
 * @since 6/8/2015
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Value("${clients}")
    private String[] listOfClients;

    private final Environment env;

    @Autowired
    public DatabaseConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {

        LOG.info("List of clients configuration: {}", listOfClients);

        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (String tenantId : listOfClients) {
            HikariDataSource dataSource = setDataSource(tenantId);
            resolvedDataSources.put(tenantId, dataSource);
        }

        /**
         * Create the final multi-tenant source.
         * It needs a default database to connect to.
         * Make sure that the default database is actually an empty tenant database.
         * Don't use that for a regular tenant if you want things to be safe!
         */
        MultitenantDataSource dataSource1 = new MultitenantDataSource();
        dataSource1.setDefaultTargetDataSource(defaultDataSource());
        dataSource1.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource1.afterPropertiesSet();

        return dataSource1;
    }

    /**
     * Creates the default data source for the application
     *
     * @return
     */
    private DataSource defaultDataSource() throws PropertyVetoException {
        return setDataSource("default");
    }

    private HikariDataSource setDataSource(String tenantId) throws PropertyVetoException {
        HikariDataSource dataSource = new HikariDataSource();
        LOG.info(env.getProperty("clients"));
        LOG.info(env.getProperty(tenantId + ".datasource.driver-class-name"));
        dataSource.setDriverClassName(env.getProperty(tenantId + ".datasource.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty(tenantId + ".datasource.url"));
        dataSource.setUsername(env.getProperty(tenantId + ".datasource.username"));
        dataSource.setPassword(env.getProperty(tenantId + ".datasource.password"));
        dataSource.setMaximumPoolSize(Constant.MAXPOOLSIZE);
        return dataSource;
    }

    private static class MultitenantDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            if (getUserSession().getCompany() != null) {
                return StringUtils.deleteWhitespace(getUserSession().getCompany()).toLowerCase();
            }
            return getUserSession().getCompany();
        }

        private static SessionVO getUserSession() {
            SessionVO userSessionVO = null;
            SecurityContext context = SecurityContextHolder.getContext();
            if (context != null && context.getAuthentication() != null) {
                Object principal = context.getAuthentication().getPrincipal();
                if (principal instanceof SessionVO) {
                    userSessionVO = (SessionVO) context.getAuthentication().getPrincipal();
                }
            }
            //setting a default one for Testcases and JPA during launch of application.
            if (userSessionVO == null) {
                userSessionVO = new SessionVO();
                userSessionVO.setCompany("gold");
            }
            return userSessionVO;
        }
    }
}

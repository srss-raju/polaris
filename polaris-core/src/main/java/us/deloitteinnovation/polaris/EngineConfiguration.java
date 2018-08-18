package us.deloitteinnovation.polaris;

import com.google.gson.Gson;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.backoff.ExponentialRandomBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import us.deloitteinnovation.polaris.common.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Polaris Engine Application Configuration
 *
 * @author Rajesh Bongurala
 * @since 12/1/2015
 */

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class EngineConfiguration {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public RetryTemplate deadlockRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        Map<Class<? extends Throwable>, Boolean> map = new HashMap<>();
        map.put(CannotAcquireLockException.class, true);
        map.put(LockAcquisitionException.class, true);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(Constant.POLICY, map);
        ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy = new ExponentialRandomBackOffPolicy();
        exponentialRandomBackOffPolicy.setMaxInterval(Constant.MAXINTERVAL);
        retryTemplate.setBackOffPolicy(exponentialRandomBackOffPolicy);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        return retryTemplate;
    }

    /**
     * Thread Pool Task Executor
     * Core Pool = Available Processors
     * Max Pool = Available Processors * 2
     *
     * @return Task Executor
     */
    @Bean
    public ThreadPoolTaskExecutor poolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        taskExecutor.setMaxPoolSize(availableProcessors * 2);
        taskExecutor.setCorePoolSize(availableProcessors);
        taskExecutor.setQueueCapacity(availableProcessors * 2);
        taskExecutor.setKeepAliveSeconds(Constant.KEEPALIVESECONDS);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }
}

package us.deloitteinnovation.polaris.security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.*;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Security Configuration
 *
 * @author ltornquist
 * @version 9/27/16
 * @since 3.0.0
 */
public class SecurePropertyContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(SecurePropertyContextInitializer.class);
    private static final String KEY = "0xkKp889CLa50dbbj26Am1n8NwBM6BZq";
    private static final String ENCRYPTION_PREFIX = "{cipher}";

    private StringEncryptor encryptor;

    private void initializeCipher() {
        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(KEY);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        pooledPBEStringEncryptor.setConfig(config);
        this.encryptor = pooledPBEStringEncryptor;

    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        initializeCipher();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String, Object> overrides = new LinkedHashMap<>();
        for (PropertySource<?> source : environment.getPropertySources()) {
            decrypt(source, overrides);
        }
        if (!overrides.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("decrypted", overrides));
        }
    }



    private void decrypt(PropertySource<?> source, Map<String, Object> overrides) {
        if (source instanceof EnumerablePropertySource) {
            EnumerablePropertySource<?> enumerable = (EnumerablePropertySource<?>) source;
            for (String key : enumerable.getPropertyNames()) {
                String value = source.getProperty(key).toString();
                if (value.startsWith(ENCRYPTION_PREFIX)) {
                    value = value.substring(ENCRYPTION_PREFIX.length());
                    try {
                        value = encryptor.decrypt(value);
                        LOG.debug("Decrypted: key={%s}", key);
                    }
                    catch (Exception e) {
                        LOG.warn(String.format("Cannot decrypt: key=%s", key), e);
                        value = "";
                    }
                    overrides.put(key, value);
                }
            }
        } else if (source instanceof CompositePropertySource) {
            for (PropertySource<?> nested : ((CompositePropertySource) source).getPropertySources()) {
                decrypt(nested, overrides);
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 15;
    }
}

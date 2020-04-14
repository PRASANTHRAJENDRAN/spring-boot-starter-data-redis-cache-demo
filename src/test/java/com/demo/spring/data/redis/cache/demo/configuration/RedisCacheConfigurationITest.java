package com.demo.spring.data.redis.cache.demo.configuration;

import com.demo.spring.data.redis.cache.demo.SpringBootStarterDataRedisDemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This integration Test requires following external dependencies to run:
 * 1.Redis
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootStarterDataRedisDemoApplication.class)
class RedisCacheConfigurationITest {

    @Autowired
    private RedisCacheManager redisCacheManager = null;

    @Autowired
    private com.demo.spring.data.redis.cache.demo.configuration.RedisCacheConfiguration.CacheConfigurationProperties cacheConfigurationProperties = null;

    @Test
    void redisCacheConfigurationPropertiesTest() {
        Map<String, String> cacheMap = cacheConfigurationProperties.getCachesTTL();
        Map<String, RedisCacheConfiguration> redisCacheConfiguration = redisCacheManager.getCacheConfigurations();
        cacheMap.forEach((key, value) -> assertEquals(redisCacheConfiguration.get(key).getTtl().getSeconds(), Long.parseLong(value)));
    }

}

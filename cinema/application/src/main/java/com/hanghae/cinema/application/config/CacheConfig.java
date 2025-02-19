package com.hanghae.cinema.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.hanghae.cinema.application.CacheType;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public SimpleCacheManager localCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();


        List<CaffeineCache> caches =
                Arrays.stream(CacheType.values())
                        .map(cache -> new CaffeineCache(
                                cache.getName(),
                                Caffeine.newBuilder()
                                        .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                        .maximumSize(cache.getMaximumSize())
                                        .recordStats()
                                        .build()
                        )).collect(Collectors.toList());


        cacheManager.setCaches(caches);

        return cacheManager;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)))
                .entryTtl(Duration.ofHours(1));
    }
}

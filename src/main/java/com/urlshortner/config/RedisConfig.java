package com.urlshortner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortner.model.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    // setting up the redis template object
    @Bean
    RedisTemplate<String, UrlDto> redisTemplate() {
        final Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(UrlDto.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        final RedisTemplate<String, UrlDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

} //ENDCLASS

package com.urlshortner.controller;

import com.urlshortner.exception.TinyUrlError;
import com.urlshortner.model.UrlDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/url")
public class TinyUrlController {

    @Autowired
    private RedisTemplate<String, UrlDto> redisTemplate;

    @Value("${redis.ttl}")
    private String ttl;

    @RequestMapping(value = "/encode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody final String url) {

        // using commons-validator library to validate the input URL
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if(!urlValidator.isValid(url)) {
            // invalid url return HTTP 400 bad request
            return ResponseEntity.badRequest().body(new TinyUrlError("invalid url"));
        }

        // if valid url, generate a hash key using guava's murmur3 hashing algorithm
        final UrlDto urlDto = UrlDto.create(url);
        log.info("shortUrl generated = {}", urlDto.getShortUrl());

        // store both hashing key and url object in redis
        redisTemplate.opsForValue().set(urlDto.getShortUrl(), urlDto, Long.parseLong(ttl), TimeUnit.SECONDS);

        // return the generated shortUrl as response header
        return ResponseEntity.noContent().header("shortUrl", urlDto.getShortUrl()).build();
    }


    @RequestMapping(value = "/decode/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUrl(@PathVariable final String id) {

        // get from Redis
        final UrlDto urlDto = redisTemplate.opsForValue().get(id);
        if(Objects.isNull(urlDto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TinyUrlError("no such key exists"));
        } else {
            log.info("url retrieved = {}", urlDto.getUrl());
        }

        return ResponseEntity.ok(urlDto.getUrl());
    }

} //ENDCLASS

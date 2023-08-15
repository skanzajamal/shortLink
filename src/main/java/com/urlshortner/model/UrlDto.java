package com.urlshortner.model;

import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UrlDto {

    private final String id;
    private final String shortUrl;
    private final String url;
    private final LocalDateTime created;

    public static UrlDto create(final String url) {

        final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
        final String shortUrl = "http://short.est/" + id;
        return new UrlDto(id, shortUrl, url, LocalDateTime.now());
    }

} //ENDCLASS

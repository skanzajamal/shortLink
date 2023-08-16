package com.urlshortner.controller;

import com.urlshortner.TinyUrlApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Objects;

@AutoConfigureMockMvc
public class TinyUrlControllerTest extends TinyUrlApplicationTest {

    @Autowired
    TinyUrlController tinyUrlController;

    @Test
    public void encode_To_ShortUrl_Then_Decode_To_Original_Url() {

        String url = "http://www.ya.ru";
        var encoder = tinyUrlController.create(url);
        var shortUrl = Objects.requireNonNull(encoder.getHeaders().get("shortUrl")).get(0);
        Assertions.assertEquals("http://short.est/34d0355d", shortUrl);

        var originalUrl = tinyUrlController.getUrl("34d0355d");
        Assertions.assertEquals(url, originalUrl.getBody());

    }

} //ENDCLASS

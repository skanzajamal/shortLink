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

        String url = "https://www.ebay.com/itm/374855555288?_trkparms=amclksrc%3DITM%26aid%3D1110013%26algo%3DHOMESPLICE.SIMRXI%26ao%3D1%26asc%3D20221122152010%26meid%3D902bd3b8e170415085b41872f50f05d6%26pid%3D101635%26rk%3D3%26rkt%3D9%26itm%3D374855555288%26pmt%3D1%26noa%3D0%26pg%3D4375194%26algv%3DSimplAMLv5PairwiseWebNoToraCoCoViewsNoHighIdfOrSortByFinalScoreBlender%26brand%3DDell&_trksid=p4375194.c101635.m3021&amdata=cksum%3A374855555288902bd3b8e170415085b41872f50f05d6%7Cenc%3AAQAIAAABIHuU2NWr2iCtJh0k4sTXQGMbASZuGJ1XF3G14rh566Zg4M4WE4Y%252B7EzcE2hqFXf7LL8Gts8CJWDFb71BUAL9tLGB8YRX4TgVrGg%252FC11Myb5kuCH2FRnmYSw2cFUDfCHXoLBCyFeMnJ2aVPLZ%252F0dsg19QbyxnzsVj0JbkSe1RrvOyeWSUqru7%252BtDu3bua7uo8YfPb2RS05x45qx3yTcaIR6Kz%252BLJVbLogOzL%252F2bt3Xv9z0dWBAelixDIg5g9E%252FDQwM%252FedyjzVy7sJwbVBaBmdq0tQD1gztnLUiBLNqQ6zv1YWlBpLNzya%252B1cEYY5HcgJmkJzY1oDoon9H0t0tBIzsSlnI534F757oMLRjMpssB3905hnog0zJPcsNefK08UAY6A%253D%253D%7Campid%3APL_CLK%7Cclp%3A4375194&_trkparms=parentrq%3A03eec00d18a0acda67b7f689ffff7954%7Cpageci%3Ab1c3876f-3d0b-11ee-b332-d2e68cbd0f84%7Ciid%3A1%7Cvlpname%3Avlp_homepage";
        var encoder = tinyUrlController.create(url);
        var shortUrl = Objects.requireNonNull(encoder.getHeaders().get("shortUrl")).get(0);
        Assertions.assertEquals("http://short.est/171904f9", shortUrl);

        var originalUrl = tinyUrlController.getUrl("171904f9");
        Assertions.assertEquals(url, originalUrl.getBody());

    }

} //ENDCLASS

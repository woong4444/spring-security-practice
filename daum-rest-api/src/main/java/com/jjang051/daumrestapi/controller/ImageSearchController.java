package com.jjang051.daumrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageSearchController {
    private final RestClient restClient = RestClient.create();
    @GetMapping("/image-search")
    @ResponseBody
    @CrossOrigin(originPatterns = {"http://127.0.0.1:5500","http://localhost:5500","http://localhost:*"})
    //@CrossOrigin(origin = "http://127.0.0.1:5500")
    public Map<String,Object> seachImage(@RequestParam(name="query") String query) {
        return restClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.scheme("https")
                                .host("dapi.kakao.com")
                                .path("/v2/search/image")
                                .queryParam("query",query)
                                .build()
                )
                .header("Authorization","KakaoAK c2b6828b16439b8dd302446432809501")
                .retrieve()
                .body(Map.class);
    }

    @GetMapping("/image")
    public String imageForm() {
        return "image";
    }
}

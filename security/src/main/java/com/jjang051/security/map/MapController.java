package com.jjang051.security.map.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Controller
@RequestMapping("/map")
@Slf4j
public class MapController {

    @Autowired
    private ObjectMapper objectMapper;
    private final RestClient restClient = RestClient.create();

    @GetMapping("/map01")
    public String map01(Model model) {
        String address = "경기도 고양시 일산동구 중앙로1275번길 38-10";
        String placeName = "학원";
        model.addAttribute("address", address);
        model.addAttribute("placeName", placeName);
        return "map/map01";
    }

    @GetMapping("/ev-car-main")
    public String evCarMain(Model model) {
        return "map/ev-car-main";
    }

    @GetMapping("/ev-car")
    @ResponseBody
    public Map<String,Object> evCar(Model model) {
        log.info("map/ev-car");
        String json = restClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .scheme("https")
                                .host("bigdata.kepco.co.kr")
                                .path("/openapi/v1/EVchargeManage.do")
                                .queryParam("addr", "서울")
                                .queryParam("apiKey", "4U5Lg0oJlM4ivG53BRtvtemmuDW8o73bz3Ci27i3")
//                                .queryParam("returnType","json")
                                .build()
                )
                .retrieve()
                .body(String.class);
        return objectMapper.readValue(json,Map.class);
    }
}
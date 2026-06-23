package com.jjang051.security.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {
    private final StringRedisTemplate stringRedisTemplate;
    @GetMapping("/set")
    public String set() {
        stringRedisTemplate.opsForValue().set("test:name","jjang051");
        return "Redis 저장완료";
    }
    @GetMapping("/get")
    public String get() {
       return stringRedisTemplate.opsForValue().get("test:name");

    }
    @GetMapping("/ttl")
    public String ttl() {
       stringRedisTemplate.opsForValue().set("auth:code","123456", Duration.ofMinutes(3));
       return "ttl 저장완료";
    }
    @GetMapping("/ttl-get")
    public Long ttlGet() {
        return stringRedisTemplate.getExpire("auth:code");

    }

}

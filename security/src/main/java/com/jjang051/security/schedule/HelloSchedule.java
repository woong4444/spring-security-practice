package com.jjang051.security.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class HelloSchedule {
    //@Scheduled(fixedDelay = 10000) //작업시간 + 10초
    //@Scheduled(fixedRate = 10000) //무조건 10초마다 한번씩
    //@Scheduled(cron = "0 54 11 * * *") //초 분 시 일 월 요일
    public void hello() {
        log.info("==============");
        log.info("Schedule Start");
        log.info("현재시간 : {}", LocalDateTime.now());
        log.info("==============");
    }
}
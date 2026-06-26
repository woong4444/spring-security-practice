package com.jjang051.security.schedule;


import com.jjang051.security.board.dao.BoardLikeRedisDao;
import com.jjang051.security.board.service.BoardRedisLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeSyncSchedule {
    private final StringRedisTemplate redisTemplate;
    private final BoardLikeRedisDao boardLikeRedisDao;

    @Scheduled(fixedDelay = 10000)
    public void syncRedisToDB() {
        log.info("syncRedisToDB");
        Set<String> keys = redisTemplate.keys("board:like:*");
        //log.info("keys : {}", keys);
        for(String key : keys) {
            int boardNo =   Integer.parseInt(key.replace("board:like:", ""));
            //log.info("boardNo: {}", boardNo);
            Set<String> redisUsers = redisTemplate.opsForSet().members(key);
            //log.info("redisUsers: {}", redisUsers);
            List<String> dbUserList =  boardLikeRedisDao.findLikeUsersByBoardNo(boardNo); //10 ,{aaa,bbb}
            Set<String> dbUsers =new HashSet<>(dbUserList);
            for(String userId:redisUsers) {
                if(!dbUsers.contains(userId)) {
                    boardLikeRedisDao.insertLike(boardNo, userId);
                    log.info("좋아요 insert boardNo = {}, userId = {}", boardNo, userId);
                }
            }
            for(String userId:dbUsers) {
                if(!redisUsers.contains(userId)) {
                    boardLikeRedisDao.deleteLike(boardNo, userId);
                    log.info("좋아요 delete boardNo = {}, userId = {}", boardNo, userId);
                }
            }
        }
    }
}
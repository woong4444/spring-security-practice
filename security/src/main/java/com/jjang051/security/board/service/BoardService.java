package com.jjang051.security.board.service;

import com.jjang051.security.board.dto.BoardDto;
import com.jjang051.security.board.dao.BoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    private final StringRedisTemplate stringRedisTemplate;
    public int write(BoardDto boardDto) {
        boardDto.setHit(0);
        boardDto.setRegDate(LocalDateTime.now());
        return boardDao.write(boardDto);
    }

    public List<BoardDto> findAll() {
        return boardDao.findAll();
    }
    public BoardDto findByNo(int no) {
        return boardDao.findByNo(no);
    }
    public void increaseHit(int no,String userKey) {
        //"board:view:7:jjang051","viewed"
        String redisKey = "board:view:" + no+":"+userKey;
        Boolean isFirstView =
                stringRedisTemplate.opsForValue()
                        .setIfAbsent(redisKey,"viewed", Duration.ofHours(24));
        if(Boolean.TRUE.equals(isFirstView)) {
            boardDao.increaseHit(no);
        }
    }

}
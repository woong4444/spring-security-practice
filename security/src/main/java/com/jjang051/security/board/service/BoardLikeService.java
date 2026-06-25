package com.jjang051.security.board.service;

import com.jjang051.security.board.dao.BoardLikeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeDao boardLikeDao;
    public int insertLike(int boardNo, String userId) {
        return boardLikeDao.insertLike(boardNo, userId);
    }
    public int likeCount(int boardNo) {
        return boardLikeDao.likeCount(boardNo);
    }
}
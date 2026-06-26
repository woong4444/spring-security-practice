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

    public boolean toggleLike(int boardNo,String userId) {
        int count = boardLikeDao.existLike(boardNo,userId);
        if(count>0){
            boardLikeDao.deleteLike(boardNo,userId);
            return false;
        } else {
            boardLikeDao.insertLike(boardNo,userId);
            return true;
        }
    }

    public int insertLike(int boardNo, String userId) {
        return boardLikeDao.insertLike(boardNo, userId);
    }
    public int deleteLike(int boardNo, String userId) {
        return boardLikeDao.deleteLike(boardNo, userId);
    }
    public int likeCount(int boardNo) {
        return boardLikeDao.likeCount(boardNo);
    }
    public boolean isLiked(int boardNo, String userId) {
        return boardLikeDao.existLike(boardNo,userId) > 0;
        //0보다 크면 좋아요 누른거
    }
}
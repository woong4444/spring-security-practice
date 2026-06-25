package com.jjang051.security.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardLikeDao {
    int insertLike(@Param("boardNo") int boardNo,
                   @Param("userId") String userId);
    int likeCount(int boardNo);
}
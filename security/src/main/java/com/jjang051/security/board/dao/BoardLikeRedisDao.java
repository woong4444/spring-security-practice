package com.jjang051.security.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardLikeRedisDao {
    int insertLike(@Param("boardNo") int boardNo,
                   @Param("userId") String userId);
    int deleteLike(@Param("boardNo") int boardNo, @Param("userId") String userId);

    List<String> findLikeUsersByBoardNo(int boardNo);
}
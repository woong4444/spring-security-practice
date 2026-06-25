package com.jjang051.security.board.dao;

import com.jjang051.security.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    int write(BoardDto boardDto);
    List<BoardDto> findAll();
    int increaseHit(int no);
    BoardDto findByNo(int no);
}
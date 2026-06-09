package com.jjang051.mybatis.board.dao;

import com.jjang051.mybatis.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    int writeBoard(BoardDto boardDto);
    BoardDto viewBoard(int no);
    List<BoardDto> listBoard();
}

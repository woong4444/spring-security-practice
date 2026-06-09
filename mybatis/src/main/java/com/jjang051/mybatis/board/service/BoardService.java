package com.jjang051.mybatis.board.service;

import com.jjang051.mybatis.board.dao.BoardDao;
import com.jjang051.mybatis.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    public int writeBoard(BoardDto boardDto) {
        return boardDao.writeBoard(boardDto);
    }
    public BoardDto viewBoard(int no) {
        return boardDao.viewBoard(no);
    }
    public List<BoardDto> listBoard() {
        return boardDao.listBoard();
    }
}

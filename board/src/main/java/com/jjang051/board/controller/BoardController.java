package com.jjang051.board.controller;

import com.jjang051.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")

public class BoardController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/write")
    public String write() {
        return "write";
    }
    @PostMapping("/write")
    public String writeProcess(@ModelAttribute BoardDto boardDto) {
        String sql = """
                INSERT INTO
                	board (no,title,content,nickname,regdate,hit)
                	VALUES (board_seq.nextval,?,?,?,sysdate,0)
                """;
        jdbcTemplate.update(sql,
                boardDto.getTitle(),
                boardDto.getContent(),
                boardDto.getNickname());
        return "redirect:/";
    }
}

package com.jjang051.board.controller;

import com.jjang051.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")

public class BoardController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public String list(Model model) {
        String sql = "SELECT * FROM board";
        List<BoardDto> boardList = jdbcTemplate.query(sql,(rs, rownum)->
                BoardDto.builder()
                        .no(rs.getInt("no"))
                        .title(rs.getString("title"))
                        .nickname(rs.getString("nickname"))
                        .build()
        );
        model.addAttribute("boardList",boardList);
        return "list";
    }


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

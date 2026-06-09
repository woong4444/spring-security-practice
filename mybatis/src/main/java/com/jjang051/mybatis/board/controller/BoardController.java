package com.jjang051.mybatis.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }
    @PostMapping("/write")
    @ResponseBody
    public String write(@RequestParam String content){
        System.out.println(content);
        return "board/write";
    }
}

package com.jjang051.security.board.controller;

import com.jjang051.security.board.dto.BoardDto;
import com.jjang051.security.board.service.BoardLikeService;
import com.jjang051.security.board.service.BoardService;
import com.jjang051.security.member.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardLikeService boardLikeService;
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }
    @PostMapping("/write")
    public String writeProcess(@ModelAttribute BoardDto boardDto,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boardDto.setAuthor(customUserDetails.getMemberDto().getUserName());
        boardService.write(boardDto);
        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.findAll();
        log.info("list={}",boardList.size());
        model.addAttribute("boardList", boardList);
        return "board/list";
    }
    @GetMapping("/view")
    public String view(Model model, @RequestParam int no,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userKey= customUserDetails.getMemberDto().getUserId();
        boardService.increaseHit(no,userKey);
        BoardDto boardDto = boardService.findByNo(no);
        int likeCount = boardLikeService.likeCount(no);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("likeCount", likeCount);
        return "board/view";
    }
    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> like (@RequestParam int no,
                                     @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userId =   customUserDetails.getMemberDto().getUserId();
        boardLikeService.insertLike(no,userId);
        int likeCount = boardLikeService.likeCount(no);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("likeCount", likeCount);
        resultMap.put("message", "success");
        return resultMap;
    }
}
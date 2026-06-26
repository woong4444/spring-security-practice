package com.jjang051.security.board.controller;

import com.jjang051.security.board.dto.BoardDto;
import com.jjang051.security.board.dto.LikeResultDto;
import com.jjang051.security.board.service.BoardLikeService;
import com.jjang051.security.board.service.BoardRedisLikeService;
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
    private final BoardRedisLikeService boardRedisLikeService;
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
    /*
    @GetMapping("/view")
    public String view(Model model, @RequestParam int no,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userKey= customUserDetails.getMemberDto().getUserId();
        boolean liked = false;
        boardService.increaseHit(no,userKey);
        BoardDto boardDto = boardService.findByNo(no);
        int likeCount = boardLikeService.likeCount(no);
        liked = boardLikeService.isLiked(no,userKey);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("liked", liked);
        return "board/view";
    }
     */
    /*
    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> like (@RequestParam int no,
                                     @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userId =   customUserDetails.getMemberDto().getUserId();
        //toggle
        boolean liked = boardLikeService.toggleLike(no,userId);
        int likeCount = boardLikeService.likeCount(no);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("likeCount", likeCount);
        resultMap.put("message", "success");
        resultMap.put("liked", liked);
        return resultMap;
    }
     */
    @GetMapping("/view")
    public String view(Model model, @RequestParam int no,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userKey= customUserDetails.getMemberDto().getUserId();
        BoardDto boardDto = boardService.findByNo(no);
        boolean liked = false;
        long likeCount = boardRedisLikeService.getLikeCount(no);
        liked = boardRedisLikeService.isLiked(no, userKey);
        model.addAttribute("liked", liked);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("boardDto", boardDto);
        return "board/view";
    }
    @PostMapping("/like")
    @ResponseBody
    public Map<String,Object> like (@RequestParam int no,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userId =   customUserDetails.getMemberDto().getUserId();
        LikeResultDto likeResultDto = boardRedisLikeService.toggleLike(no,userId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "success");
        resultMap.put("likeCount", likeResultDto.getLikeCount());
        resultMap.put("liked", likeResultDto.isLiked());
        return resultMap;
    }
}
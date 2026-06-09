package com.jjang051.mybatis.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/write")
    @ResponseBody
    public String write(@RequestParam String content) {
        System.out.println(content);
        return "board/write";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("upload") MultipartFile upload) {
        String fileName = UUID.randomUUID() + "_" + upload.getOriginalFilename();
        Path uploadPath = Paths.get("C:/upload/" + fileName);
        try {
            if (!Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path saveFile =  uploadPath.resolve(fileName);
            Files.copy(upload.getInputStream(), saveFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "/upload/"+fileName;
    }
}

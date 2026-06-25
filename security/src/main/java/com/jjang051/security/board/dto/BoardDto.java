package com.jjang051.security.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private int no;
    private String title;
    private String content;
    private String author;
    private int hit;
    private LocalDateTime regDate;
}
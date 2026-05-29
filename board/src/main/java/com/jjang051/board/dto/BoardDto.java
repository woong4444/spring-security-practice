package com.jjang051.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
    private int no;
    private String title;
    private String content;
    private String nickname;
    private int hit;
    private LocalDateTime regDate;
}

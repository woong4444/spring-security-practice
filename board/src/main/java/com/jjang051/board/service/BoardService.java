package com.jjang051.board.service;

import com.jjang051.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final JdbcTemplate jdbcTemplate;
    public List<BoardDto> findAll() {
        String sql = "SELECT * FROM board ORDER BY NO DESC";
        List<BoardDto> boardList = jdbcTemplate.query(sql,(rs, rownum)->
                BoardDto.builder()
                        .no(rs.getInt("no"))
                        .title(rs.getString("title"))
                        .nickname(rs.getString("nickname"))
                        .build()
        );
        return boardList;
    }
    public void increaseHit(int no) {
        String updateSql = "UPDATE board SET hit = hit+1  WHERE NO=?";
        jdbcTemplate.update(updateSql,no);
    }
    public BoardDto findByNo(int no) {
        String sql = "SELECT * FROM board WHERE NO=?";
        BoardDto boardDto = jdbcTemplate.queryForObject(sql,(rs,rownum)->
                        BoardDto.builder()
                                .no(rs.getInt("no"))
                                .title(rs.getString("title"))
                                .content(rs.getString("content"))
                                .nickname(rs.getString("nickname"))
                                .hit(rs.getInt("hit"))
                                .regDate(rs.getTimestamp("regdate").toLocalDateTime())
                                .build(),
                no
        );
        return boardDto;
    }
    public void write(BoardDto boardDto) {
        String sql = """
                INSERT INTO
                	board (no,title,content,nickname,regdate,hit)
                	VALUES (board_seq.nextval,?,?,?,sysdate,0)
                """;
        jdbcTemplate.update(sql,
                boardDto.getTitle(),
                boardDto.getContent(),
                boardDto.getNickname());
    }

}

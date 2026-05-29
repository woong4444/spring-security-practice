package com.jjang051.movie.controller;

import com.jjang051.movie.dto.MovieDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
//    @RequestMapping(value = "/list",method = RequestMethod.POST)
//    public String test() {
//        return "list";
//    }
    // jdbc connection   -->  jdbcTemplate  -->  mybatis(sql을 분리 (xml)) -->
    // jpa(java method로 db 컨트롤)
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String username = "spring";
    private final String password = "1234";

    @GetMapping("/list")
    public String list(Model model) {
        //String sql = "SELECT no,title,reserve_yn as reserveYn FROM movie";
        String sql = "SELECT * FROM movie";
        List<MovieDto> movieList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int no = rs.getInt("no");
                String title = rs.getString("title");
                //int reserveYn = rs.getInt("reserveYn");
                String reserveYn = rs.getString("reserve_yn");
                MovieDto movieDto = MovieDto.builder()
                        .no(no)
                        .title(title)
                        .reserveYn(reserveYn)
                        .build();
                movieList.add(movieDto);
            }
            System.out.println(movieList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("movieList",movieList);
        return "list";
    }
    @PostMapping("/write")
    public String write(@RequestParam(name="title", required = true) String title) {
        String sql = "INSERT INTO movie VALUES (movie_seq.nextval,?,'N')";
        Connection conn = null;
//        if(title==null || title.trim().isEmpty()){
//            return """
//                    <script>
//                        alert('영화제목을 입력하세요.');
//                        location.href='/movie/list';
//                    </script>
//                    """;
//        }
        try {
            //공백 또는 안쓰고 넘어온거 경고창 띄우기
            conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/movie/list";
    }
    @PostMapping("/reserve")
    public String reserve(@RequestParam(name="no", required = true) int no) {
        String sql = "UPDATE movie SET reserve_yn = 'Y' where no = ?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/movie/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(name="no", required = true) int no) {
        String sql = "DELETE FROM movie where no = ?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/movie/list";
    }
}

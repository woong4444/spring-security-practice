package com.jjang051.tododb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;


@Controller
public class TodoController {
    @GetMapping("/db-test")
    @ResponseBody
    public String dbTest() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "spring";
        String password = "1234";
        String sql = "select 'db 연결 성공' as message from dual";
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("message");
            } else {
                return "결과없음";
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return "db 연결 실패 : "+e.getMessage();
        }
    }
}

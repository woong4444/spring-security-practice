package com.jjang051.tododb.controller;

import com.jjang051.tododb.dto.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    @GetMapping("/list")
    //@ResponseBody
    public String list(Model model) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "spring";
        String password = "1234";
        String sql = "select * from todo";
        List<Todo> todoList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs  = pstmt.executeQuery();
            while(rs.next()) {
                int no = rs.getInt("no");
                String content = rs.getString("content");
                String complete = rs.getString("complete");

                Todo todo = new Todo(no,content,complete);
                todoList.add(todo);
                System.out.println(no + "/" + content + "/" + complete);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("todoList",todoList);
        return "list";
    }

    @PostMapping("/write")
    public String write(@RequestParam(name="todo",required = true) String todo) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "spring";
        String password = "1234";
        String sql = "INSERT INTO todo (NO,content,complete) VALUES (todo_seq.nextval,?,'N')";
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,todo);
            int result = pstmt.executeUpdate();
            System.out.println("result==="+result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/list";
    }
    @PostMapping("/complete")
    public String complete(@RequestParam(name="index", required = true) int index) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "spring";
        String password = "1234";
        String sql = "UPDATE todo SET  complete = 'Y' WHERE NO=?";
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,index);
            int result = pstmt.executeUpdate();
            System.out.println("result==="+result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(name="index", required = true) int index) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "spring";
        String password = "1234";
        String sql = "DELETE FROM  todo WHERE NO=?";
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,index);
            int result = pstmt.executeUpdate();
            System.out.println("result==="+result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/list";
    }
}

package com.jjang051.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    private List<String> todoList = new ArrayList<>();
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("todoList",todoList);
        return "list";
    }
    @PostMapping("/write")
    public String write(
            @RequestParam(name="todo", required = true) String todo
            ) {
        todoList.add(todo); //산책,공부
        System.out.println(todoList.toString());
        return "redirect:/list";
    }
}

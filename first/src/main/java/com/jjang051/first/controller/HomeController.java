package com.jjang051.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//controller라는 annotation은 요청을 처리하는 곳이다.
@Controller
public class HomeController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(
            @RequestParam(name="title",required = false, defaultValue = "") String title,
            @RequestParam(name="nickname",required = false, defaultValue = "") String name
    ) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>%s</title>
                </head>
                <body>
                    <h1>hello %s</h1>
                    <h2>hi</h2>
                </body>
                </html>
                """.formatted(title,name);
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hi(@RequestParam(name="age", defaultValue = "0",required = false) int age,
                     @RequestParam(name="name", defaultValue = "",required = false) String name
                     ) {
        return """
               <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>이름과 나이</title>
                </head>
                <body>
                    <h1>name %s</h1>
                    <h2>age = %d</h2>
                </body>
                </html>
               """.formatted(name,age);
    }
    @GetMapping("/user")
    @ResponseBody
    public Map<String,String> user() {
        Map<String,String>  hashMap = new HashMap<>();
        hashMap.put("userID","jjang051");
        return hashMap;
    }

    @GetMapping("/template01")
    public String template01(
    @RequestParam(name="userID",required = true) String userID,
    @RequestParam(name="userPW",required = true) String userPW,
    Model model
                             ) {
        model.addAttribute("userID",userID);
        model.addAttribute("userPW",userPW);
        return "template01";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String loginProcess(@RequestParam(name="userID", required = true) String userID,
                               @RequestParam(name="userPW", required = true) String userPW
                               ) {
        if(userID.equals("jjang051") && userPW.equals("1234")){
            return "login-ok";
        } else {
            return "login-fail";
        }
    }
}

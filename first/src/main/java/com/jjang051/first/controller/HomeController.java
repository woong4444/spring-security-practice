package com.jjang051.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}

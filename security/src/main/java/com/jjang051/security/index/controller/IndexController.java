package com.jjang051.security.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/","/main","/index","/home"})
    public String index() {
        return "index/index";
    }
}

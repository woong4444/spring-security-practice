package com.jjang051.quiz01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//IoC(Inversion on Control) 제어의 역전
@Controller
public class QuizController {
    @GetMapping("/hello")
    //@ResponseBody
    public String hello(@RequestParam(
                            name="name",
                            required = false,
                            defaultValue = "noname")
                            String userName,
                            Model model
                        ) {
        System.out.println(userName);
        model.addAttribute("name",userName);
        return "hello";
    }

    @GetMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam(name="num01",required = true,defaultValue = "0") int num01,
                       @RequestParam(name="num02",required = true,defaultValue = "0") int num02,
                       @RequestParam(name="op",required = true,defaultValue = "0") String op
                       ) {
        int result = 0;
        if(op.equals("+")) {
            result = num01+num02;
        } else if(op.equals("-")){
            result = num01-num02;
        } else if(op.equals("*")) {
            result = num01*num02;
        } else if(op.equals("/")){
            result = num01/num02;
        }
        return "결과 : "+result;
    }
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam(name="id",required = true) String userID,
                        @RequestParam(name="pw",required = true) String userPW
                        ) {
        if(userID.equals("admin") && userPW.equals("1234")) {
            return "login ok";
        } else {
            return "login fail";
        }
    }
    @GetMapping("/bmi")
    @ResponseBody
    public String login(@RequestParam(name="height",required = true) double height,
                        @RequestParam(name="weight",required = true) double weight
    ) {
        double meterHeight =  height/100;
        double bmi =  weight / (meterHeight*meterHeight);
        return String.format("BMI : %.2f",bmi);
    }
    @GetMapping("/user-info")
    @ResponseBody
    public Map<String,Object> userInfo(
            @RequestParam(name="userId",required = true) String userId,
            @RequestParam(name="userName",required = true) String userName,
            @RequestParam(name="age",required = true) int age
    ) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("userName",userName);
        map.put("age",age);
        return map;
    }
}

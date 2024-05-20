package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {
    @GetMapping("/members")
    @ResponseBody
    String members() { //회원가입된 상태에서만 보여지게 하고싶음
        return "memberlist";
    }
}

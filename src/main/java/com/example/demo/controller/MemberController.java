package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.persistence.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;

    @RequestMapping("/new")
    public String newMember() {
        return "new-form";
    }

    @RequestMapping("/add")
    public String addMember(@ModelAttribute Member member, Model model) {
        memberRepository.save(member);
        model.addAttribute(member);
        return "add-result";
    }

    @RequestMapping("/all")
    public ModelAndView allMember() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");
        mv.addObject("members", members);

        return mv;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "<html><body><h1>Hello, ResponseBody!</h1></body></html>";
    }

    @GetMapping("/api/{id}")
    public String getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id).getName();
    }

    @PostMapping("/requestBody")
    public String requestBodyHandler(@RequestBody Member member) {
        log.info("member = {}", member);
        return "ok";
    }

    @PostMapping("/requestBody2")
    public Member requestBodyHandler2(@RequestBody Member member) {
        log.info("name = {}", member.getName());
        log.info("age = {}", member.getAge());
        return member;
    }
}

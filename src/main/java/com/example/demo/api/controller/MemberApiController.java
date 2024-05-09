package com.example.demo.api.controller;

import com.example.demo.api.request.SearchParam;
import com.example.demo.domain.Member;
import com.example.demo.repository.SpringDataMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {
    private final SpringDataMemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> members() {
        return memberRepository.findAll();
    }

    @GetMapping("/member/search")
    public List<Member> memberSearch(@ModelAttribute SearchParam searchParam) {
        return memberRepository.findByNameLike(searchParam.getName());
    }

    @GetMapping("/member/{memberId}")
    public Member member(@PathVariable Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }

    @PostMapping("/member")
    public List<Member> memberCreate(@RequestBody Member member) {
        memberRepository.save(member);
        return memberRepository.findAll();
    }

    @PutMapping("/member/{memberId}")
    public List<Member> memberUpdate(@RequestBody Member member, @PathVariable Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElse(null);
        findMember.setName(member.getName());
        findMember.setAge(member.getAge());
        return memberRepository.findAll();
    }
}

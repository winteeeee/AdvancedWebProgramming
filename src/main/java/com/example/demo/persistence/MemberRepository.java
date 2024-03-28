package com.example.demo.persistence;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private static final List<Member> members = new ArrayList<>();
    private static Long sequence = 0L;

    public void save(Member member) {
        member.setId(++sequence);
        members.add(member);
    }

    public List<Member> findAll() {
        return members;
    }

    public Member findById(Long memberId) {
        Member findMember = members.stream()
                .filter(member -> memberId == member.getId())
                .findAny()
                .orElse(null);

        return findMember;
    }
}

package com.kit.dormitory.member;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("subMemberStorage")
public class FileMemberStorage implements MemberStorage {
    private static Map<Long,Member> members = new HashMap<>();
    @Override
    public void store(Member member) {
        System.out.println("=============to file Storage==============");
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        System.out.println("=============from file Storage==============");
        return members.get(memberId);
    }
}

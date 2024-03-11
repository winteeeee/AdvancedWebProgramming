package com.kit.dormitory.member;

import java.util.HashMap;
import java.util.Map;

public class DbMemberStorage implements MemberStorage{
    private static Map<Long,Member> members = new HashMap<>();
    @Override
    public void store(Member member) {
        System.out.println("=============to db Storage==============");
        members.put(member.getId(), member);
    }
    @Override
    public Member findById(Long memberId) {
        System.out.println("=============from db Storage==============");
        return members.get(memberId);
    }
}

package com.kit.dormitory.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberStorage memberStorage = new FileMemberStorage();
    private final MemberStorage memberStorage;

    @Autowired
    public MemberServiceImpl(MemberStorage memberStorage) {
        this.memberStorage = memberStorage;
    }

    @Override
    public void register(Member member) {
        memberStorage.store(member);
    }
    @Override
    public Member findMember(Member member) {
        return memberStorage.findById(member.getId());
    }
}

package com.kit.dormitory.member;

import com.kit.dormitory.annotation.ElapsedTimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberStorage memberStorage = new FileMemberStorage();
    private final MemberStorage memberStorage;

    @Autowired
    public MemberServiceImpl(MemberStorage memberStorage) {
        this.memberStorage = memberStorage;
    }

    @ElapsedTimeLog
    @Override
    public void register(Member member) {
        memberStorage.store(member);
    }

    @ElapsedTimeLog
    @Override
    public Member findMember(Member member) {
        return memberStorage.findById(member.getId());
    }
}

package com.kit.dormitory.member;

public class MemberServiceImpl implements MemberService{

    private final MemberStorage memberStorage = new FileMemberStorage();

    @Override
    public void register(Member member) {
        memberStorage.store(member);
    }
    @Override
    public Member findMember(Member member) {
        Member findMember = memberStorage.findById(member.getId());
        return findMember;
    }
}

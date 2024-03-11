package com.kit.dormitory.member;

public interface MemberStorage {
    void store(Member member);
    Member findById(Long memberId);
}

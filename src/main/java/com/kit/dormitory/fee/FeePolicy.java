package com.kit.dormitory.fee;

import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;

public interface FeePolicy {
    int fee(Member member, DormName dormName);
}

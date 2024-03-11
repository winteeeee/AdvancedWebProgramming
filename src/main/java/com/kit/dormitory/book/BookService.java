package com.kit.dormitory.book;

import com.kit.dormitory.member.Member;

public interface BookService {
    Book assignRoom(Member member, DormName dormName, String roomNumber);
}

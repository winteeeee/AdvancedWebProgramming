package com.kit.dormitory.book;

import com.kit.dormitory.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
    private Member member;
    private DormName dormName;
    private int fee;

    public Book(Member member, DormName dormName, int fee) {
        this.member = member;
        this.dormName = dormName;
        this.fee = fee;
    }
}

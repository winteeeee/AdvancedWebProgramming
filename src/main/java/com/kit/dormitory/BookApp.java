package com.kit.dormitory;

import com.kit.dormitory.book.Book;
import com.kit.dormitory.book.BookService;
import com.kit.dormitory.book.BookServiceImpl;
import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberService;
import com.kit.dormitory.member.MemberServiceImpl;

public class BookApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        BookService bookService = new BookServiceImpl();
        Long memberId=1L;
        Member member = new Member(memberId,"kim",1);
        memberService.register(member);
        Member findMember = memberService.findMember(member);
        Book book = bookService.assignRoom(findMember, DormName.PUREUM,"101");
        System.out.println("book = " + book);
    }
}

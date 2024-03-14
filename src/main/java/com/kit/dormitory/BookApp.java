package com.kit.dormitory;

import com.kit.dormitory.book.Book;
import com.kit.dormitory.book.BookService;
import com.kit.dormitory.book.BookServiceImpl;
import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberService;
import com.kit.dormitory.member.MemberServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        BookService bookService = ac.getBean("bookService", BookService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"kim",1);
        memberService.register(member);
        Member findMember = memberService.findMember(member);

        Book book = bookService.assignRoom(findMember, DormName.PUREUM,"101");
        System.out.println("book = " + book);
    }
}

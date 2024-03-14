package com.kit.dormitory;

import com.kit.dormitory.book.BookService;
import com.kit.dormitory.book.BookServiceImpl;
import com.kit.dormitory.fee.FeePolicy;
import com.kit.dormitory.fee.NewFeePolicy;
import com.kit.dormitory.fee.OldFeePolicy;
import com.kit.dormitory.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    MemberService memberService() {
        return new MemberServiceImpl(memberStorage());
    }

    @Bean
    BookService bookService() {
        return new BookServiceImpl(feePolicy());
    }

    @Bean
    MemberStorage memberStorage() {
        return new DbMemberStorage();
    }

    @Bean
    FeePolicy feePolicy() {
        return new NewFeePolicy();
    }
}

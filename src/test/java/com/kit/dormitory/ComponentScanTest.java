package com.kit.dormitory;

import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentScanTest {
    @Test
    void comScanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);

        Member member = new Member(1L, "kim", 1);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.register(member);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

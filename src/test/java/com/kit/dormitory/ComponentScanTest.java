package com.kit.dormitory;

import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentScanTest {
    
    private final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);
    @Test
    void comScanTest() {
        Member member = new Member(1L, "kim", 1);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.register(member);
        Member member1 = memberService.findMember(member);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    
    @Test
    void AOP_Test() {
        //AOP를 적용하면 bean 반환 시 실제 클래스가 아니라 프록시가 반환됨
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        
        Class c = memberService.getClass();
        for (Method method : c.getDeclaredMethods()) {
            System.out.println("method.getName() = " + method.getName());
        }
    }
}

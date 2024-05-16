package com.example.demo.service;

import com.example.demo.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Disabled
class MemberServiceTest {
    @Autowired
    private MemberService 멤버_서비스;
    @Test
    public void 회원가입() {
        Member 멤버 = new Member();
        멤버.setName("kim");
        Long 저장한_멤버_아이디 = 멤버_서비스.join(멤버);

        assertThat(멤버)
                .as(() -> "가입 회원과 조회 회원은 같아야 함")
                .isEqualTo(멤버_서비스.findOne(저장한_멤버_아이디));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        Member 멤버1 = new Member();
        멤버1.setName("김");
        Member 멤버2 = new Member();
        멤버2.setName("김");

        멤버_서비스.join(멤버1);
        assertThatThrownBy(() -> {
            멤버_서비스.join(멤버2);
        }, "중복 예외가 발생해야 한다.")
                .isInstanceOf(IllegalArgumentException.class);
    }
}
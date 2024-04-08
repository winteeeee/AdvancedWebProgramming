package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class JpaMemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void simpleTest() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findOne(member.getId());

        Assertions.assertSame(member, findMember, () -> member + "와 " + findMember + "는 같아야 함"); //단언
    }

    @Test
    void 나이테스트() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Member("kim", 0));
        //assertThrows의 인자는 Executable을 받음. 따라서 그냥 new만 하면 안되고 () -> 를 통해서 executable을 구현해야함
        System.out.println("message = " + exception.getMessage());
    }

    @DisplayName("회원생성")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @ParameterizedTest
    @DisplayName("나이 유효성 검사")
    @ValueSource(ints = {10, 130})
    void createMember(int age) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Member("kim", age));
        System.out.println("message = " + exception.getMessage());
    }
}
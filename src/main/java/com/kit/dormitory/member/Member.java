package com.kit.dormitory.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String name;
    private int grade;

    public Member(Long id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}

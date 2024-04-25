package com.example.demo.domain;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private int age;

    public Member(String name, int age) {
        if (age >= 100 || age <= 20) {
            throw new IllegalArgumentException("나이는 20보다 크고 100보다 작아야 함");
        }

        this.name = name;
        this.age = age;
    }

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();
}

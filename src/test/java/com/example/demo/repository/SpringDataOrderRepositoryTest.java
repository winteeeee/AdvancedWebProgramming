package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SpringDataOrderRepositoryTest {
    @Autowired
    private SpringDataOrderRepository springDataOrderRepository;
    
    @Test
    void 기본조회() {
        List<Order> all = springDataOrderRepository.findWithMemberGraph();
        all.forEach(o -> System.out.println("o.getMember().getName() = " + o.getMember().getName()));
    }
}
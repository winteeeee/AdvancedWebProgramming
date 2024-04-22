package com.example.demo.repository;

import com.example.demo.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SpringDataItemRepositoryTest {
    @Autowired
    private SpringDataItemRepository springDataItemRepository;

    @Test
    void 특정가격_이상_상품검색() {
        List<Item> items = springDataItemRepository.findByPriceGreaterThanEqual(20000);
        items.stream().forEach(i -> System.out.println("i.getPrice() = " + i));
    }
    
    @Test
    void 이름포함_상품검색() {
        List<Item> items = springDataItemRepository.findByNameLike("spring%");
        items.forEach(System.out::println);
    }
}
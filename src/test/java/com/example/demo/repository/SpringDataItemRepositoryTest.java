package com.example.demo.repository;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.ItemSellingStatus;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Disabled
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

    @Test
    void 페이징_테스트() {
        PageRequest firstPage = PageRequest.of(0, 2);
        PageRequest secondPage = PageRequest.of(1, 2);
        Page<Item> firstItems = springDataItemRepository.findAll(firstPage);
        firstItems.forEach(System.out::println);
        System.out.println("=================================================");
        Page<Item> secondItems = springDataItemRepository.findAll(secondPage);
        secondItems.forEach(System.out::println);
    }

    @Test
    void Page_Slice() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "price"));
        Slice<Item> sliceBy = springDataItemRepository.findSliceBy(pageRequest); 
    }
    
    @Test
    void 판매상태로_찾기() {
        Book book1 = new Book();
        book1.setName("Book1");
        book1.setPrice(10000);
        book1.setSellingStatus(ItemSellingStatus.SELLING);

        Book book2 = new Book();
        book2.setName("Book2");
        book2.setPrice(10000);
        book2.setSellingStatus(ItemSellingStatus.HOLD);

        Book book3 = new Book();
        book3.setName("book3");
        book3.setPrice(10000);
        book3.setSellingStatus(ItemSellingStatus.STOP_SELLING);

        springDataItemRepository.saveAll(List.of(book1, book2, book3));
        List<Item> findItems = springDataItemRepository.findAllBySellingStatusIn(List.of(ItemSellingStatus.SELLING, ItemSellingStatus.HOLD));
        assertThat(findItems)
                .hasSize(2)
                .extracting("name", "price", "sellingStatus")
                .containsExactlyInAnyOrder(tuple("Book1", 10000, ItemSellingStatus.SELLING), tuple("Book2", 10000, ItemSellingStatus.HOLD));
    }

    @Test
    void 가격으로검색_JPQL() {
        List<Item> items = springDataItemRepository.findByPriceLessThan(20000);
        items.forEach(System.out::println);
    }
}
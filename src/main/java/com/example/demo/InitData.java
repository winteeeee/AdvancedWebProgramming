package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.SpringDataItemRepository;
import com.example.demo.repository.SpringDataMemberRepository;
import com.example.demo.repository.SpringDataOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitData {
    private final InitService initService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() throws IOException {
        log.info("데이터 초기화 시작");
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    public static class InitService {
        private final SpringDataMemberRepository memberRepository;
        private final SpringDataItemRepository itemRepository;
        private final SpringDataOrderRepository orderRepository;

        public void init() throws IOException {
            initMember();
            initItem();
            initOrder();
        }
        
        private void initMember() {
            initMember("kim", 21, Address.builder()
                    .city("city1")
                    .street("street1")
                    .zipcode("zipcode1")
                    .build());
            initMember("lee", 30, Address.builder()
                    .city("city2")
                    .street("street2")
                    .zipcode("zipcode2")
                    .build());
        }

        private void initMember(String name, int age, Address address) {
            Member member = Member.builder()
                    .name(name)
                    .age(age)
                    .address(address)
                    .build();
            
            memberRepository.save(member);
        }

        private void initItem() {
            initItem("JPA1", 10000, 100, "jAuthor1");
            initItem("JPA2", 20000, 100, "jAuthor2");
            initItem("Spring1", 10000, 100, "sAuthor1");
            initItem("Spring2", 20000, 200, "sAuthor2");
        }

        private void initItem(String name, int price, int stockQuantity, String author) {
            Item book = Book.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity)
                    .author(author)
                    .build();

            itemRepository.save(book);
        }
        
        private void initOrder() {
            Member kim = memberRepository.findFirstByName("kim");
            Item JPA1 = itemRepository.findFirstByNameLike("%JPA1%");
            Item JPA2 = itemRepository.findFirstByNameLike("%JPA2%");

            Member lee = memberRepository.findFirstByName("lee");
            Item Spring1 = itemRepository.findFirstByNameLike("%Spring1%");
            Item Spring2 = itemRepository.findFirstByNameLike("%Spring2%");
            
            initOrder(kim, 
                    Delivery.builder()
                            .address(kim.getAddress())
                            .build(),
                    OrderItem.createOrderItem(JPA1, JPA1.getPrice(), 1),
                    OrderItem.createOrderItem(JPA2, JPA2.getPrice(), 1));

            initOrder(lee,
                    Delivery.builder()
                            .address(lee.getAddress())
                            .build(),
                    OrderItem.createOrderItem(Spring1, Spring1.getPrice(), 1),
                    OrderItem.createOrderItem(Spring2, Spring2.getPrice(), 1));
        }
        
        private void initOrder(Member member, Delivery delivery, OrderItem... orderItems) {
            Order order = Order.builder()
                    .member(member)
                    .delivery(delivery)
                    .orderItems(orderItems)
                    .build();
            
            orderRepository.save(order);
        }
    }
}

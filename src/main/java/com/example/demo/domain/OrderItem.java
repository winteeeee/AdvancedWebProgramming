package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import com.example.demo.domain.item.Item;

import jakarta.persistence.*;
@Getter
@Setter
@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}

package com.example.demo.domain;

import lombok.Builder;
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

    @Builder
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }
    public void cancel() {
        getItem().addStock(count);
    }
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}

package com.example.demo.api.response;

import com.example.demo.domain.OrderItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    private String itemName;
    private int orderPrice;
    private int count;

    public static OrderItemResponse of(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .itemName(orderItem.getItem().getName())
                .orderPrice(orderItem.getOrderPrice())
                .count(orderItem.getCount())
                .build();
    }
}

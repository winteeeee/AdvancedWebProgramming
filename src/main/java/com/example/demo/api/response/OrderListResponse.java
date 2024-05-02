package com.example.demo.api.response;

import com.example.demo.domain.Address;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderListResponse {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public static OrderListResponse of(Order order) {
        return OrderListResponse.builder()
                .orderId(order.getId())
                .name(order.getMember().getName())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .address(order.getDelivery().getAddress())
                .build();
    }
}

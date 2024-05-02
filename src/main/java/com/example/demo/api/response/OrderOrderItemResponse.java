package com.example.demo.api.response;

import com.example.demo.domain.Address;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderOrderItemResponse {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemResponse> orderItems;

    public static OrderOrderItemResponse of(Order order) {
        return OrderOrderItemResponse.builder()
                .orderId(order.getId())
                .name(order.getMember().getName())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .address(order.getDelivery().getAddress())
                .orderItems(order.getOrderItems().stream().map(OrderItemResponse::of).collect(Collectors.toList()))
                .build();
    }
}

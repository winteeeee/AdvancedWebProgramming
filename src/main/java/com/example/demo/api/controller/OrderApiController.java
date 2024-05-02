package com.example.demo.api.controller;

import com.example.demo.api.response.ApiResponse;
import com.example.demo.api.response.OrderListResponse;
import com.example.demo.api.response.OrderOrderItemResponse;
import com.example.demo.api.response.ResponseCode;
import com.example.demo.domain.Order;
import com.example.demo.repository.SpringDataOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {
    private final SpringDataOrderRepository orderRepository;

    @GetMapping("/v1/orders")
    public List<Order> orderV1() {
        return orderRepository.findAll();
    }

    @GetMapping("v2/orders")
    public List<OrderListResponse> orderV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderListResponse::of).collect(Collectors.toList());
    }

    @GetMapping("v3/orders")
    public List<OrderOrderItemResponse> orderV3() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderOrderItemResponse::of).collect(Collectors.toList());
    }

    @GetMapping("v4/orders")
    public ApiResponse<List<OrderOrderItemResponse>> orderV4() {
        List<Order> orders = orderRepository.findAll();
        List<OrderOrderItemResponse> data = orders.stream().map(OrderOrderItemResponse::of).toList();
        return ApiResponse.response(ResponseCode.OK, data);
    }
}

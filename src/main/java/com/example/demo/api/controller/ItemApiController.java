package com.example.demo.api.controller;

import com.example.demo.api.request.ItemCreateRequest;
import com.example.demo.api.response.ApiResponse;
import com.example.demo.api.response.ItemResponse;
import com.example.demo.api.response.ResponseCode;
import com.example.demo.api.service.ItemApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemApiController {
    private final ItemApiService itemService;
    @PostMapping("/create")
    public ApiResponse<ItemResponse> createItem(@RequestBody
                                                ItemCreateRequest request){
        ItemResponse res = itemService.createItem(request);
        return ApiResponse.response(ResponseCode.OK, res);
    }
}
package com.example.demo.api.response;

import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.ItemSellingStatus;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ItemResponse {
    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;
    private ItemSellingStatus sellingStatus;
    public static ItemResponse of(Item item){
        return ItemResponse.builder()
                .itemId(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .sellingStatus(item.getSellingStatus())
                .build();
    }
}
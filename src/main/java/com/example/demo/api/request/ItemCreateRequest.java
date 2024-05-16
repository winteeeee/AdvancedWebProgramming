package com.example.demo.api.request;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.ItemSellingStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequest{
    private String name;
    private int price;
    private int stockQuantity;
    private ItemSellingStatus sellingStatus;
    public Item toEntity(){
        return Book.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .sellingStatus(this.sellingStatus)
                .build();
    }
}
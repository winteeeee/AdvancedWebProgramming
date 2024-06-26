package com.example.demo.domain.item;

import com.example.demo.exception.NotEnoughStockException;
import lombok.*;
import com.example.demo.domain.Category;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
@ToString
@SuperBuilder
@NoArgsConstructor
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @Enumerated(EnumType.STRING)
    private ItemSellingStatus sellingStatus;

    @ManyToMany(mappedBy="items")
    @ToString.Exclude
    @Builder.Default
    private List<Category> categories = new ArrayList<Category>();

    public void addStock(int quantity) { //입고, 주문취소
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) { //주문
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException(this.stockQuantity, quantity);
        }
    }
}

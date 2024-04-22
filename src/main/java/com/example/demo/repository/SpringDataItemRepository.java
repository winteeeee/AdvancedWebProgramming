package com.example.demo.repository;

import com.example.demo.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPriceGreaterThanEqual(int price);
    List<Item> findByNameLike(String name);
}

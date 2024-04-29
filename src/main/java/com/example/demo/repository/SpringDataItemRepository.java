package com.example.demo.repository;

import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.ItemSellingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPriceGreaterThanEqual(int price);
    List<Item> findByNameLike(String name);
    Page<Item> findPageBy(Pageable pageable);
    Slice<Item> findSliceBy(Pageable pageable);
    List<Item> findAllBySellingStatusIn(List<ItemSellingStatus> sellingStatuses);
    @Query("SELECT i FROM Item i WHERE i.price <= ?1")
    List<Item> findByPriceLessThan(int price);
    @Query("SELECT new com.example.demo.dto.BasicItemDTO(i.name, i.price) FROM Item i WHERE i.price <= :price")
    List<Item> findByPriceLessThan3(int price);
    Item findFirstByNameLike(String name);
}

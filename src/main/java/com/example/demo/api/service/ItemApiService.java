package com.example.demo.api.service;

import com.example.demo.api.request.ItemCreateRequest;
import com.example.demo.api.response.ItemResponse;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.SpringDataItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemApiService {
    private final SpringDataItemRepository itemRepository;
    public ItemResponse createItem(ItemCreateRequest request){
        Item savedItem = itemRepository.save(request.toEntity());
        return ItemResponse.of(savedItem);
    }
}
package com.example.demo.service;

import com.example.demo.domain.item.Album;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Disabled
public class ItemServiceTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    EntityManager em;

    @Test
    public void 음반_상품등록() throws Exception {
        Item item = new Album();
        item.setName("music");
        ((Album)item).setArtist("artist");

        item.setPrice(20000);
        item.addStock(50);

        Item saveItem = itemService.saveItem(item);
        em.flush();

        assertThat(saveItem)
                .as("저장한 아이템과 조회한 아이템은 같아야 함")
                .isEqualTo(itemService.findOne(saveItem.getId()));
    }
}

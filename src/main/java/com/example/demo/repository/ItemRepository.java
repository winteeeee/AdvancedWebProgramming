package com.example.demo.repository;

import com.example.demo.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public Item save(Item item) {
        if (item.getId() == null) { //비영속
            em.persist(item);
        } else {
            em.merge(item);
        }

        return item;
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

    public List<Item> findByName(String name) {
        return em.createQuery("SELECT i from Item i WHERE i.name = :itemName", Item.class)
                .setParameter("itemName", name)
                .getResultList();
    }
}

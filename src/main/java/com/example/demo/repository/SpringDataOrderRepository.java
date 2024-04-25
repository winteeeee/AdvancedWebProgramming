package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataOrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.member")
    List<Order> findWithMemberJPQL();
    @EntityGraph(value = "order.member.graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o FROM Order o")
    List<Order> findWithMemberGraph();
}

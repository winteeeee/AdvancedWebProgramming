package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaMemberRepository implements MemberRepository{
    @Autowired
    EntityManager em;
    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    @Override
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name",
                Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

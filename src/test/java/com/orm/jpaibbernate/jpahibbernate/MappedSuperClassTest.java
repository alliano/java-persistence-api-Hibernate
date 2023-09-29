package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Post;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class MappedSuperClassTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Post post = new Post();
        post.setId(UUID.randomUUID().toString().substring(0, 5));
        post.setName("alliano-enginner");
        post.setTitle("What's backpreasure condition");
        post.setContent("One of us know that the app will recive many much request");
        post.setCreatedAt(LocalDateTime.now());
        entityManager.persist(post);

        transaction.commit();
    }
}
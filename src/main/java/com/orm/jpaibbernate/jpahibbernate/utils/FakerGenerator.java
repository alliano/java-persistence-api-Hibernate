package com.orm.jpaibbernate.jpahibbernate.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.orm.jpaibbernate.jpahibbernate.entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import net.datafaker.Faker;

public class FakerGenerator {

    private EntityManagerFactory entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();

    private Faker faker = new Faker();
    

    public void run() {
       insertFakerPost(100000);
    }

    private void insertFakerPost(int range) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (int i = 0; i < range; i++) {
            Post post = new Post();
            post.setId(String.valueOf(i).concat(UUID.randomUUID().toString().substring(0, 5)));
            post.setName(this.faker.name().fullName());
            post.setContent(this.faker.howToTrainYourDragon().dragons());
            post.setTitle("example post "+ String.valueOf(i));
            post.setUpdatedAt(LocalDateTime.now());
            entityManager.persist(post);
        }

        transaction.commit();
        entityManager.close();
    }
    
    
}

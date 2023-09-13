package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Ewallet;
import com.orm.jpaibbernate.jpahibbernate.entities.User;
import com.orm.jpaibbernate.jpahibbernate.entities.entityUtils.Genre;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OneToOneTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testInsertOneToMany() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = new User();
        user.setName("Alliano");
        user.setGenre(Genre.MALE);
        entityManager.persist(user);

        Ewallet ewallet = new Ewallet();
        ewallet.setEmail("alliano@gmail.com");
        ewallet.setBalance(10_000_000L);
        ewallet.setUser(user);

        entityManager.persist(ewallet);
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testFindOneToMany() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class, "6d9ab61f-20da-4905-8494-80adf01c5cd7");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Alliano", user.getName());
        Assertions.assertEquals(10_000_000L, user.getEwallet().getBalance());
        transaction.commit();
        entityManager.close();
    }
}

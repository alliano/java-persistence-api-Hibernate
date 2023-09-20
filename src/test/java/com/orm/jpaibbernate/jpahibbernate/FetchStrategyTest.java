package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Mahasiswa;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class FetchStrategyTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @AfterEach
    public void tireDown() {
        this.entityManagerFactory.close();
    }

    @Test
    public void testLazyAndEager() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.find(Mahasiswa.class, 3);
        

        transaction.commit();
    }

}

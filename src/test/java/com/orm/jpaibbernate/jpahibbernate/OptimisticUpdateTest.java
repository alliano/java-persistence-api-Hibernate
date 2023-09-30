package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Dosen;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OptimisticUpdateTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void test() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Dosen dosen = new Dosen();
        dosen.setId("001");
        dosen.setName("ferdo");
        dosen.setEmail("fedro@gmail.com");
        Thread.sleep(10 * 1000);
        entityManager.persist(dosen);

        transaction.commit();
    }

    @Test
    public void rest2() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Dosen dosen = new Dosen();
        dosen.setId("002");
        dosen.setName("ndoe");
        dosen.setEmail("ndoe@gmail.com");
        entityManager.persist(dosen);

        transaction.commit();
    }

    @Test
    public void testUpdate1() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Dosen dosen = entityManager.find(Dosen.class, "001");
        dosen.setName("throw exception");
        dosen.setUpdatedAt(LocalDateTime.now());
        dosen.setUpdatedAt(LocalDateTime.now());
        Thread.sleep(10 * 1000L);
        /**
         * ini akan di rollback, karena 
         * pada method test2 berhasil mengupdate duluan
         */ 
        entityManager.persist(dosen);
        entityManager.close();
    }

    @Test
    public void testUpdate2() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Dosen dosen = entityManager.find(Dosen.class, "001");
        dosen.setName("Name Updated");
        dosen.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(dosen);

        transaction.commit();
        entityManager.close();
    }
}

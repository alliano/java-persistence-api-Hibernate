package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Mahasiswa;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;

public class PesimisticLockingTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testPesimistic1() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        /**
         * pada saat melakukan find, kita tambahkan mode locking nya di paramterter ke 3
         */
        Mahasiswa mahasiswa = entityManager.find(Mahasiswa.class, "3", LockModeType.PESSIMISTIC_WRITE);
        mahasiswa.setEmail("liano@gmail.com");
        Thread.sleep(10 * 1000L);
        entityManager.persist(mahasiswa);
        transaction.commit();
    }

    @Test
    public void testPesimistic2() throws InterruptedException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Mahasiswa mahasiswa = entityManager.find(Mahasiswa.class, "3", LockModeType.PESSIMISTIC_WRITE);
        mahasiswa.setEmail("lian@gmail.com");
        entityManager.persist(mahasiswa);
        transaction.commit();
    }
}

package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Dosen;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class ManagedUnManagedEntityTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void test() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // ini merupakan Unmanaged Entity
        Dosen dosen = new Dosen();
        dosen.setId("D001");
        dosen.setEmail("example@Gmail.com");
        dosen.setCreatedAt(LocalDateTime.now());
        dosen.setUpdatedAt(LocalDateTime.now());
        /**
         * Saat kita melakukan persis pada entity diatas
         * maka entity diatas akan menjadi managed entity
         * artinya enity diatas akan dimanage oleh JPA
         *  */ 
        entityManager.persist(dosen);

        /**
         * dan seandainya setelah melakukan persistterjadi perubahan,
         * misalnya name di ubah dsb dan kita tidak melakukan
         * merge atau persist, maka secara otomatis jpa akan melakukan update
         */
        dosen.setEmail("kisworo@gmail.com");
        transaction.commit();
    }

    @Test
    public void find() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // dosen merupakan managed entity
        Dosen dosen = entityManager.find(Dosen.class, "D001");
        /**
         * entity dosen menajdi Unmanaged entity, dan jikala
         * terjadi perubahan di bject entity dosen
         * misalnya seperti nama email nya di ubah dsb
         * maka JPA tidak akan melakukan update secara otomatis karena emg
         * entity tersebut sudah tidak di manage oleh JPA.
         * 
         * namun jikalau kita melaukan persist kembali maka entity tersebut
         * menjadi managed entity
         *  */ 
        entityManager.detach(dosen);

        transaction.commit();
    }
}

package com.orm.jpaibbernate.jpahibbernate;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.InternationalStudent;
import com.orm.jpaibbernate.jpahibbernate.entities.TranferStudent;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class IS_A_SingleTableTest {
    
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

        TranferStudent tranferStudent = new TranferStudent();
        tranferStudent.setId("TF001");
        tranferStudent.setName("Jeka");
        tranferStudent.setNpm("0129346");
        tranferStudent.setFromUniversity("Havard University");
        tranferStudent.setIpk(3.55);
        tranferStudent.setMovingDate(Date.from(Instant.now()));
        entityManager.persist(tranferStudent);

        InternationalStudent internationalStudent = new InternationalStudent();
        internationalStudent.setId("IN001");
        internationalStudent.setName("Natch");
        internationalStudent.setFromCountry("Japan");
        internationalStudent.setNpm("0982457");
        internationalStudent.setIpk(3.70);
        internationalStudent.setVisa("012384750982");
        entityManager.persist(internationalStudent);
        
        transaction.commit();
    }
}

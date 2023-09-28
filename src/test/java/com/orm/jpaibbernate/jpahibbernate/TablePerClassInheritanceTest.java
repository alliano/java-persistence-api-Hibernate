package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Transaction;
import com.orm.jpaibbernate.jpahibbernate.entities.TransactionCredit;
import com.orm.jpaibbernate.jpahibbernate.entities.TransactionDebit;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class TablePerClassInheritanceTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @AfterEach
    public void tierDown() {
        this.entityManagerFactory.close();
    }

    @Test
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("TR01");
        transaction.setBelance(10_000_000L);
        transaction.setCreatedAt(LocalDateTime.now());
        entityManager.persist(transaction);


        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setId("TRD01");
        transactionDebit.setDebitAmount(100_000_000L);
        transactionDebit.setCreatedAt(LocalDateTime.now());
        entityManager.persist(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setId("TRC01");
        transactionCredit.setCreditAmount(100_000_000L);
        transactionCredit.setCreatedAt(LocalDateTime.now());
        entityManager.persist(transactionCredit);

        entityTransaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Transaction transaction = entityManager.find(Transaction.class, "TR01");
        Assertions.assertNotNull(transaction);

        entityTransaction.commit();
    }
    
    @Test
    public void findByChildEntity() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, "TRC01");
        Assertions.assertNotNull(transactionCredit);
    }
}

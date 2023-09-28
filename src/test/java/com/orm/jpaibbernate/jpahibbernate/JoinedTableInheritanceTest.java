package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.PaymentBankJago;
import com.orm.jpaibbernate.jpahibbernate.entities.PaymentBankPermata;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class JoinedTableInheritanceTest {
    
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

        PaymentBankJago paymentBankJago = new PaymentBankJago();
        paymentBankJago.setId("JGO01");
        paymentBankJago.setAmount(100_000_000_00L);
        paymentBankJago.setCardNumber("0232847592342453");
        entityManager.persist(paymentBankJago);

        PaymentBankPermata paymentBankPermata = new PaymentBankPermata();
        paymentBankPermata.setId("PRM01");
        paymentBankPermata.setAmount(30_000_000_00L);
        paymentBankPermata.setCardNumber("000229345234562");
        entityManager.persist(paymentBankPermata);

        transaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        PaymentBankJago bankJago = entityManager.find(PaymentBankJago.class, "JGO01");
        Assertions.assertNotNull(bankJago);

        PaymentBankPermata bankPermata = entityManager.find(PaymentBankPermata.class, "PRM01");
        Assertions.assertNotNull(bankPermata);

        transaction.commit();
    }
}

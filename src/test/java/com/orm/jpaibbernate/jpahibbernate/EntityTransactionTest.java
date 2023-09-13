package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class EntityTransactionTest {
    

    @Test
    public void testTransaction() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            // operasi sql
           

            transaction.commit();
        } catch (Throwable throwable) {
            transaction.rollback();
        }
        entityManager.close();
        Assertions.assertNotNull(transaction);   
    }
}

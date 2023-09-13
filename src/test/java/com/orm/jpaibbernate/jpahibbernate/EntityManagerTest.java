package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

public class EntityManagerTest {
    
    @Test
    public void testEntityManager() {
        // membuat instance EntityManager
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        // operasi kueri

        // selesai
        entityManager.close();
        Assertions.assertNotNull(entityManager);
    }
}

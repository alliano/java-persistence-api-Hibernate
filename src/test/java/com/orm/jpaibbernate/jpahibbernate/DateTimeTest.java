package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;
import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Category;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DateTimeTest {
    
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @Test
    public void testDateTime() {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = new Category();
            category.setName("Some Category name");
            category.setDescription("Some description");
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(Calendar.getInstance());
            this.entityManager.persist(category);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

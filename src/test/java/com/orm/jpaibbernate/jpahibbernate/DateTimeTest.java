package com.orm.jpaibbernate.jpahibbernate;

import java.time.LocalDateTime;
import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Category;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class DateTimeTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testDateTime() {
     EntityManager entityManager = this.entityManagerFactory.createEntityManager();
     EntityTransaction transaction = entityManager.getTransaction();
     transaction.begin();
     Category category = new Category();
     category.setName("Some Category name");
     category.setDescription("Some description");
     category.setCreatedAt(LocalDateTime.now());
     category.setUpdatedAt(Calendar.getInstance());
     entityManager.persist(category);
     transaction.commit();
    }
}

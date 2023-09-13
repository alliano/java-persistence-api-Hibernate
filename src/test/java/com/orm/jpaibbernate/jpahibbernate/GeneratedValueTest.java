package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Category;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class GeneratedValueTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testGeneratedValue() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Category category = new Category();
        // Disini kita tidak meng set Id nya, karena Id nya
        // secara otomatis di generate kan
        category.setName("Smartpone");
        category.setDescription("some description here");
        entityManager.persist(category);
        transaction.commit();
    }

    @Test
    public void destroy() {
     EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<Category> categories = entityManager.createQuery("""
                        SELECT c FROM Category c
                        """, Category.class).getResultList();
        if(!categories.isEmpty()) categories.forEach(c -> entityManager.remove(c));
    }
}

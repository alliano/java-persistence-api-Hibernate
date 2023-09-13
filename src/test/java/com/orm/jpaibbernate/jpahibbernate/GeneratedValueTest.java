package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Category;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class GeneratedValueTest {
    
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @Test
    public void testGeneratedValue() {
        EntityTransaction transaction = this.entityManager.getTransaction();

        try {
            transaction.begin();
            Category category = new Category();
            // Disini kita tidak meng set Id nya, karena Id nya
            // secara otomatis di generate kan
            category.setName("Smartpone");
            category.setDescription("some description here");
            this.entityManager.persist(category);
            transaction.commit();
        } catch (Throwable throwable) {
            transaction.rollback();
        }
    }

    @Test
    public void destroy() {
        List<Category> categories = this.entityManager.createQuery("""
                        SELECT c FROM Category c
                        """, Category.class).getResultList();
        categories.forEach(c -> entityManager.remove(c));
    }
}

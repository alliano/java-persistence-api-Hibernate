package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.orm.jpaibbernate.jpahibbernate.entities.Category;
import com.orm.jpaibbernate.jpahibbernate.entities.User;
import com.orm.jpaibbernate.jpahibbernate.entities.entityUtils.Genre;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class EntityListenerTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testEntityListener() {
      EntityManager entityManager = this.entityManagerFactory.createEntityManager();
      EntityTransaction transaction = entityManager.getTransaction();

      transaction.begin();
      Category category = new Category();
      category.setName("example name");
      category.setDescription("some desc 4");
      entityManager.persist(category);
      transaction.commit();
    }

    @Test @Order(value = 1)
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = new User();
        user.setName("Alliano");
        user.setGenre(Genre.MALE);
        Assertions.assertDoesNotThrow(() -> entityManager.persist(user));
        transaction.commit();
    }

    @Test @Order(value = 2)
    public void testUpdate() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<User> query = entityManager.createQuery("""
                        SELECT u FROM User AS u WHERE u.name = 'Alliano'
                        """, User.class);
        User user = query.getSingleResult();
        user.setName("Roronoa Zoro");
        Assertions.assertDoesNotThrow(() -> entityManager.merge(user));
        transaction.commit();
    }


    @Test
    public void testListnerInEntityClass() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.createQuery("""
                        SELECT u FROM User AS u WHERE u.name = 'Roronoa Zoro'
                        """, User.class).getSingleResult();
        Assertions.assertEquals("Mr.Roronoa Zoro S.kom", user.getFullName());
        System.out.println(user.getFullName());
        transaction.commit();
    }

    @Test
    public void destroy() {
        EntityManager entitymanager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();
        List<Category> categoryList = entitymanager.createQuery("""
                        SELECT c FROM Category AS c
                        """, Category.class).getResultList();
        List<User> userList = entitymanager.createQuery("""
                        SELECT u FROM User AS u
                        """, User.class).getResultList();
        if(!userList.isEmpty()) userList.forEach(u -> entitymanager.remove(u));
        if(!categoryList.isEmpty()) categoryList.forEach(c -> entitymanager.remove(c));
        transaction.commit();
    }
}

package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.orm.jpaibbernate.jpahibbernate.entities.Customer;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CrudOperationTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test @Order(value = 1)
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setId("C001");
        customer.setName("Alliano");
        // melakukan insert ke database menggunakan method persist()
        entityManager.persist(customer);
        transaction.commit();
    }

    @Test @Order(value = 2)
    public void testUpdate() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        // disini kita cukup memasukan primarykey dari data atau entity yang kita ingin update
        customer.setId("C001");
        customer.setName("Fara");
        // melakukan update
        Customer costumerUpdate = entityManager.merge(customer);
        Assertions.assertNotNull(costumerUpdate);
        Assertions.assertEquals("C001", costumerUpdate.getId());
        Assertions.assertEquals("Fara", costumerUpdate.getName());
        transaction.commit();
    }

    @Test @Order(value = 3)
    public void testSelect() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, "C001");

        Assertions.assertNotNull(customer);
        Assertions.assertEquals("C001", customer.getId());
        Assertions.assertEquals("Fara", customer.getName());
        transaction.commit();
    }

    @Test @Order(value = 4)
    public void testDelete() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, "C001");
        // menghapus data/entity
        entityManager.remove(customer);
        transaction.commit();
    }
}

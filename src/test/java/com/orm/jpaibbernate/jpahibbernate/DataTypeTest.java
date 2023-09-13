package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;

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
import jakarta.persistence.TypedQuery;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class DataTypeTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test @Order(value = 1)
    public void testDataType() {
      EntityManager entityManager = this.entityManagerFactory.createEntityManager();
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      Customer customer = new Customer();
      customer.setId("C003");
      customer.setName("Uchiha Itachi");
      customer.setPrimaryEmail("itachiUchiha@akatsuki.ac.id");
      customer.setMeried(false);
      customer.setAge((byte)32);
      Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
      transaction.commit();
    }

    @Test @Order(value = 2)
    public void destroy() {
      EntityManager entityManager = this.entityManagerFactory.createEntityManager();
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      TypedQuery<Customer> createQuery = entityManager.createQuery("""
            SELECT p FROM Customer p""", Customer.class);
      List<Customer> customerList = createQuery.getResultList();
      customerList.forEach(c -> entityManager.remove(c));
      transaction.commit();
    }
}

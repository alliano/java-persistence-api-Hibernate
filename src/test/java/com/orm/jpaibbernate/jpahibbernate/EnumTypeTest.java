package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Customer;
import com.orm.jpaibbernate.jpahibbernate.entities.CustomerType;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class EnumTypeTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testEnumType() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setId("C004");
        customer.setName("Izumi");
        customer.setPrimaryEmail("izunauchiha@uchiha.com");
        customer.setAge((byte)20);
        customer.setType(CustomerType.SILVER);
        entityManager.persist(customer);
        transaction.commit();
    }
}

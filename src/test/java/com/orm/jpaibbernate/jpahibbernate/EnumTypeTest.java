package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.orm.jpaibbernate.jpahibbernate.entities.Customer;
import com.orm.jpaibbernate.jpahibbernate.entities.CustomerType;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class EnumTypeTest {
    
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @Test @Order(value = 1)
    public void testEnumType() {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            Customer customer = new Customer();
            customer.setId("C004");
            customer.setName("Izumi");
            customer.setPrimaryEmail("izunauchiha@uchiha.com");
            customer.setAge((byte)20);
            customer.setType(CustomerType.SILVER);
            this.entityManager.persist(customer);
            transaction.commit();
        } catch(Throwable throwable) {
            transaction.rollback();
            throwable.printStackTrace();
        }
    }
}

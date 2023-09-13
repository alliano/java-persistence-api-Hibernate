package com.orm.jpaibbernate.jpahibbernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Customer;
import com.orm.jpaibbernate.jpahibbernate.entities.CustomerType;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class CollectionAttributeTest {
 
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testAttributeCollection() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        ArrayList<String> hobbies = new ArrayList<String>(List.of("Hiking", "Flxing", "Farming"));

        Customer customer = new Customer();
        customer.setId("C005");
        customer.setAge((byte)19);
        customer.setName("Alliano");
        customer.setPrimaryEmail("onanymus@yandex.com");
        customer.setType(CustomerType.SILVER);
        customer.setHobbies(hobbies);
        entityManager.persist(customer);
        transaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, "C005");

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(3, customer.getHobbies().size());
        Assertions.assertEquals("Alliano", customer.getName());
    }

    @Test
    public void testMapAtribute() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Map<String, Integer> skills = new HashMap<String, Integer>();
        skills.put("Java", 90);
        skills.put("Docker", 95);
        skills.put("Linux", 100);
        Customer customer = new Customer();
        customer.setId("C007");
        customer.setName("edgar");
        customer.setAge((byte) 20);
        customer.setMeried(false);
        customer.setPrimaryEmail("example@gmail.com");
        customer.setType(CustomerType.GOLD);
        customer.setSkills(skills);
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
        transaction.commit();
    }
    @Test
    public void testMapAtributeUpdate() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, "C007");
        customer.setSkills(new HashMap<String, Integer>(Map.of("Java", 100, "SQL", 80, "C++", 90)));
        Assertions.assertDoesNotThrow(() -> entityManager.merge(customer));
        transaction.commit();
    }

    @Test
    public void destroy() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<Customer> customerList = entityManager.createQuery("""
                        SELECT c FROM Customer AS c
                        """, Customer.class).getResultList();
        if(!customerList.isEmpty()) customerList.forEach(c -> entityManager.remove(c));
    }
}

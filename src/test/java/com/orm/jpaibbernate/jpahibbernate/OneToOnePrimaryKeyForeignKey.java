package com.orm.jpaibbernate.jpahibbernate;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Address;
import com.orm.jpaibbernate.jpahibbernate.entities.Seller;
import com.orm.jpaibbernate.jpahibbernate.entities.Store;
import com.orm.jpaibbernate.jpahibbernate.entities.embededs.Contact;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OneToOnePrimaryKeyForeignKey {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Store store = new Store();
        store.setId(1L);
        store.setEmail("allstore@gmail.com");
        store.setName("alastore");
        entityManager.persist(store);

        Address address = new Address();
        address.setId(1L);
        address.setProvince("Jawa Barat");
        address.setStreet("Mawar Mereah");
        address.setCity("Bandung");
        address.setStore(store);
        entityManager.persist(address);
        transaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Store store = entityManager.find(Store.class, 1);
        Assertions.assertNotNull(store);
        Assertions.assertEquals("alastore", store.getName());
        Assertions.assertNotNull(store.getAddress());
        Assertions.assertEquals("Jawa Barat", store.getAddress().getProvince());
        Assertions.assertEquals("Bandung", store.getAddress().getCity());
        transaction.commit();
    }

    @Test
    public void testInsertOneToOne() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String primaryKey = UUID.randomUUID().toString().substring(0, 10);
        Contact contact = new Contact();
        contact.setContactPhone("081341079104");
        contact.setFirstName("Lian");
        contact.setLastName("Sanjaya");
        contact.setEmail("lianlee@gmail.com");

        Seller seller = new Seller();
        seller.setId(primaryKey);
        seller.setContact(contact);
        seller.setStoreName("EX");
        entityManager.persist(seller);
        
        Store store = new Store();
        store.setId(1L);
        store.setName("ex");
        store.setEmail("lianlee@gmail.com");
        store.setSeller(seller);
        entityManager.persist(store);

        Address address = new Address();
        address.setId(1L);
        address.setProvince("Jawa Tengah");
        address.setCity("Tebu ireng");
        address.setCity("Banyu Mas");
        address.setStore(store);
        entityManager.persist(address);
        
        transaction.commit();
    }


    @Test
    public void testFindOneToOne() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Seller seller = entityManager.find(Seller.class, "bd497b59-d");
        Assertions.assertNotNull(seller);
        Assertions.assertNotNull(seller.getStore());
        Assertions.assertNotNull(seller.getStore().getAddress());
        transaction.commit();
    }
}

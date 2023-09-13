package com.orm.jpaibbernate.jpahibbernate;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.entities.Departement;
import com.orm.jpaibbernate.jpahibbernate.entities.Seller;
import com.orm.jpaibbernate.jpahibbernate.entities.embededs.Contact;
import com.orm.jpaibbernate.jpahibbernate.entities.embededs.DepatementId;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class EmbededTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testEmbeded() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Assertions.assertDoesNotThrow(() -> {
            Contact contact = new Contact("Alliano", "alfarez", "081341079104", "allianoonanimus@yandex.co.id");
            Seller seller = new Seller();
            seller.setId(UUID.randomUUID().toString().substring(0, 10));
            seller.setStoreName("Gatgatin.id");
            seller.setContact(contact);
            entityManager.persist(seller);
        });
        transaction.commit();
    }

    @Test
    public void testEmbededId() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        DepatementId depatementId = new DepatementId("xyzy".concat(UUID.randomUUID().toString().substring(0, 5)), "xyz".concat(UUID.randomUUID().toString().substring(0, 5)));
        Departement departement = new Departement();
        departement.setDepatementId(depatementId);
        departement.setName("some name here");
        entityManager.persist(departement);
        transaction.commit();
    }

    @Test
    public void testFindWithEmbededId() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        DepatementId depatementId = new DepatementId("xyzy", "xyz");
        Departement departement = entityManager.find(Departement.class, depatementId);
        Assertions.assertNotNull(departement);
        Assertions.assertEquals("xyzy", departement.getDepatementId().getDepartementId());

        transaction.commit();
    }
}

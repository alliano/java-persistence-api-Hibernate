package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Brand;
import com.orm.jpaibbernate.jpahibbernate.entities.Product;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class ManyToOneOneTest {
    
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

        Brand brand = new Brand();
        brand.setName("POCO");
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setName("POCO X3 NFC");
        product1.setPrice(3_000_000L);
        product1.setBrand(brand);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setName("POCO F5");
        product2.setPrice(4_000_000L);
        product2.setBrand(brand);
        entityManager.persist(product2);

        transaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, 1);
        Assertions.assertNotNull(product);
        Assertions.assertNotNull(product.getBrand());
        Assertions.assertEquals("POCO", product.getBrand().getName());
    }
}

package com.orm.jpaibbernate.jpahibbernate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Clob;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Admin;
import com.orm.jpaibbernate.jpahibbernate.entities.Image;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class LargeObejctTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testLageObject() throws IOException, URISyntaxException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Image image = new Image();
        image.setDescription("");
        byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/META-INF-persistence.png").toURI()));
        image.setImage(bytes);
        entityManager.persist(image);
        transaction.commit();
    }

    @Test
    public void testAdmin() throws IOException, URISyntaxException {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Assertions.assertDoesNotThrow(() -> {
            Blob blobProxyPersisteceImage = BlobProxy.generateProxy(Files.readAllBytes(Path.of(getClass().getResource("/images/META-INF-persistence.png").toURI())));
            Clob clobProxyDescription = ClobProxy.generateProxy("Admin for manage customer");
            Admin admin = new Admin();
            admin.setName("Admin1");
            admin.setPicture(blobProxyPersisteceImage);
            admin.setDescription(clobProxyDescription);
            entityManager.persist(admin);
        });
        transaction.commit();
    }
}

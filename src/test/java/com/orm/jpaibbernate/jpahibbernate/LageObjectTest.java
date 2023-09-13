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
import jakarta.persistence.EntityTransaction;

public class LageObjectTest {
    
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @Test
    public void testLageObject() {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            Image image = new Image();
            image.setDescription("");
            byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/META-INF-persistence.png").toURI()));
            image.setImage(bytes);
            this.entityManager.persist(image);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void testAdmin() throws IOException, URISyntaxException {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        Assertions.assertDoesNotThrow(() -> {
            Blob blobProxyPersisteceImage = BlobProxy.generateProxy(Files.readAllBytes(Path.of(getClass().getResource("/images/META-INF-persistence.png").toURI())));
            Clob clobProxyDescription = ClobProxy.generateProxy("Admin for manage customer");
            Admin admin = new Admin();
            admin.setName("Admin1");
            admin.setPicture(blobProxyPersisteceImage);
            admin.setDescription(clobProxyDescription);
            this.entityManager.persist(admin);
        });
        transaction.commit();
    }
}

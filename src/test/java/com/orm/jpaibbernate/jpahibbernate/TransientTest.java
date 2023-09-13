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
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TransientTest {
    
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @Test
    public void testTransient() throws IOException, URISyntaxException {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        Admin admin = new Admin();
        Blob imageBlob = BlobProxy.generateProxy(Files.readAllBytes(Path.of(getClass().getResource("/images/META-INF-persistence.png").toURI())));
        Clob descriptionClob = ClobProxy.generateProxy("Some description here");
        admin.setName("MadaraUchiha");
        admin.setPicture(imageBlob);
        admin.setDescription(descriptionClob);
        // nilai dari full name ini nanti tidak diikutkan dimasukan dalam database
        // karena atribut fullName di annotasi @Transient
        admin.setFullName("example fullName");
        Assertions.assertDoesNotThrow(() ->this.entityManager.persist(admin));
        transaction.commit();
    }
}

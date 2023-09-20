package com.orm.jpaibbernate.jpahibbernate;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Mahasiswa;
import com.orm.jpaibbernate.jpahibbernate.entities.MataKuliah;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class ManyToManyTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    private void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testInsert() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.setName("Pemograman Berorenteasi Object(PBO)");
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();
        mahasiswaList.addAll(List.of(
            entityManager.find(Mahasiswa.class, 3), 
            entityManager.find(Mahasiswa.class, 4 )
            ));
        mataKuliah.setMahasiswa(mahasiswaList);
        entityManager.persist(mataKuliah);
        transaction.commit();
    }

    @Test
    public void testUpdate() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Mahasiswa mahasiswa = entityManager.find(Mahasiswa.class, 4);
        mahasiswa.getMataKuliah().get(0).setName("Programming dasar");
        entityManager.merge(mahasiswa);

        transaction.commit();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Mahasiswa mahasiswa = entityManager.find(Mahasiswa.class, 3);
        Assertions.assertNotNull(mahasiswa);
        transaction.commit();
    }
}

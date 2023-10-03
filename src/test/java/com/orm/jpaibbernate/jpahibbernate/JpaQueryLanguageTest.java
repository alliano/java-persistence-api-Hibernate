package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Admin;
import com.orm.jpaibbernate.jpahibbernate.entities.Brand;
import com.orm.jpaibbernate.jpahibbernate.entities.Departement;
import com.orm.jpaibbernate.jpahibbernate.entities.Mahasiswa;
import com.orm.jpaibbernate.jpahibbernate.entities.Post;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class JpaQueryLanguageTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        this.entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
    }

    @Test
    public void testSelect() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Admin> createQuery = entityManager.createQuery("SELECT a FROM Admin AS a", Admin.class);
        List<Admin> admins = createQuery.getResultList();
        admins.forEach(admin -> {
            System.out.println("name : " + admin.getName());
        });
        Assertions.assertNotNull(admins);
        transaction.commit();
    }

    @Test
    public void testWhereClause() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Departement> createQuery = entityManager.createQuery("SELECT d FROM Departement AS d WHERE name = :name AND d.depatementId.companyId = :companyId", Departement.class);
        createQuery.setParameter("name", "Departement Ifrastructure");
        createQuery.setParameter("companyId", "DEP-IT");
        List<Departement> resultList = createQuery.getResultList();
        resultList.forEach(d -> {
            System.out.println("name : " + d.getName());
            System.out.println("company Id : " + d.getDepatementId().getCompanyId());
        });
        transaction.commit();
    }

    @Test
    public void testJoinClause() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Brand> createQuery = entityManager
            .createQuery("select b from Brand as b inner join b.product", Brand.class);
        List<Brand> resultList = createQuery.getResultList();
        resultList.forEach(b -> {
            b.getProduct().forEach(p -> {
                System.out.println("product : " + p.getName());
            });
        });
        transaction.commit();
    }

    @Test
    public void testJoinFetch() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Mahasiswa> createQuery = entityManager
            .createQuery("select m from Mahasiswa as m inner join fetch m.prodi", Mahasiswa.class);
        List<Mahasiswa> resultList = createQuery.getResultList();
        resultList.forEach(m -> {
            System.out.println("name : " + m.getName());
            m.getMataKuliah().forEach(mt -> {
                System.out.println("mata kuliah : " + mt.getName());
            });
        });
        transaction.commit();
    }

    @Test
    public void testOrderByClause() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Post> createQuery = entityManager.createQuery("select p from Post as p order by p.name desc", Post.class);
        List<Post> resultList = createQuery.getResultList();
        resultList.forEach(p -> {
            System.out.println("Username : " + p.getName());
            System.out.println("Title : " + p.getTitle());
            System.out.println("Content : " + p.getContent());
        });

        transaction.commit();
    }

    @Test
    public void testLimit() {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Post> createQuery = entityManager.createQuery("select p from Post as p order by p.name asc", Post.class);
        createQuery.setFirstResult(10);
        createQuery.setMaxResults(10);
        List<Post> resultList = createQuery.getResultList();
        resultList.forEach(p -> {
            System.out.println("name : " + p.getName());
            System.out.println("title : " + p.getTitle());
            System.out.println("content : " + p.getContent());
            System.out.println("\n");
        });
        transaction.commit();
    }
}
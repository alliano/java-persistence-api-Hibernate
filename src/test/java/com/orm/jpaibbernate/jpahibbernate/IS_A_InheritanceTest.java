package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.orm.jpaibbernate.jpahibbernate.entities.Employee;
import com.orm.jpaibbernate.jpahibbernate.entities.Manager;
import com.orm.jpaibbernate.jpahibbernate.entities.VicePresident;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class IS_A_InheritanceTest {
    
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

        Employee employee = new Employee();
        employee.setId("EMP1");
        employee.setName("Madara");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("MA1");
        manager.setName("Asta");
        manager.setTotalManager(100);
        entityManager.persist(manager);

        VicePresident vicePresident = new VicePresident();
        vicePresident.setId("VI1");
        vicePresident.setName("Luck");
        vicePresident.setTotalVicePrecident(200);
        entityManager.persist(vicePresident);

        transaction.commit();
    }
}

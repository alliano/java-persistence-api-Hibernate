package com.orm.jpaibbernate.jpahibbernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.orm.jpaibbernate.jpahibbernate.configurations.ApplicationConfiguration;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerFactoryTest {

    private ConfigurableApplicationContext configurableApplicationContext;

    @BeforeEach
    public void setUp() {
        this.configurableApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        this.configurableApplicationContext.registerShutdownHook();
    }
    
    @Test
    public void testEntityManagetFactory() {
        EntityManagerFactory entityManager1 = EntityManagerUtil.getEntityManagerFactory();
        EntityManagerFactory entityManager2 = EntityManagerUtil.getEntityManagerFactory();

        Assertions.assertNotNull(entityManager1);
        Assertions.assertNotNull(entityManager2);
        Assertions.assertSame(entityManager1, entityManager2);
    }

    @Test
    public void testBeanEntityManagerFactory() {
        EntityManagerFactory entityManagerFactory = this.configurableApplicationContext.getBean(EntityManagerFactory.class);
        EntityManagerFactory entityManagerFactory2 = this.configurableApplicationContext.getBean(EntityManagerFactory.class);
                
        Assertions.assertSame(entityManagerFactory, entityManagerFactory2);
        Assertions.assertNotNull(entityManagerFactory);

    }
}

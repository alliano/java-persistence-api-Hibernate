package com.orm.jpaibbernate.jpahibbernate;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orm.jpaibbernate.jpahibbernate.dtos.MahasiswaDto;
import com.orm.jpaibbernate.jpahibbernate.dtos.MahasiswaNameEmailDto;
import com.orm.jpaibbernate.jpahibbernate.dtos.MahasiswaSizeDto;
import com.orm.jpaibbernate.jpahibbernate.dtos.PriceListDto;
import com.orm.jpaibbernate.jpahibbernate.dtos.ProdiDto;
import com.orm.jpaibbernate.jpahibbernate.entities.Admin;
import com.orm.jpaibbernate.jpahibbernate.entities.Brand;
import com.orm.jpaibbernate.jpahibbernate.entities.Departement;
import com.orm.jpaibbernate.jpahibbernate.entities.Mahasiswa;
import com.orm.jpaibbernate.jpahibbernate.entities.Post;
import com.orm.jpaibbernate.jpahibbernate.entities.Product;
import com.orm.jpaibbernate.jpahibbernate.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

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

    @Test
    public void testNamedQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.findByTitle", Post.class);
        namedQuery.setParameter("title", "example post 10000");
        List<Post> resultList = namedQuery.getResultList();

        resultList.forEach(p -> {
            System.out.println("name : " + p.getName());
            System.out.println("title : " + p.getTitle());
            System.out.println("content : " + p.getContent());
        });

        transaction.commit();
    }

    @Test
    public void testConstructorExpression() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<MahasiswaDto> createQuery = entityManager.createQuery(
            "select new com.orm.jpaibbernate.jpahibbernate.dtos.MahasiswaDto(m.name, p.name) from Mahasiswa as m inner join m.prodi as p order by p.name asc", 
            MahasiswaDto.class);
        List<MahasiswaDto> resultList = createQuery.getResultList();
        resultList.forEach(m -> {
            System.out.println("Name :" + m.getName());
            System.out.println("Prodi :" + m.getProdi());
        });
        transaction.commit();
    }

    @Test
    public void testConstructor() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Object[]> createQuery = entityManager.createQuery("select m.name as name, p.name as prodi from Mahasiswa as m inner join m.prodi as p", Object[].class);
        List<Object[]> resultList = createQuery.getResultList();
        resultList.forEach(m -> {
            System.out.println("nama : "+ m[0]);
            System.out.println("prodi : "+ m[1]);
        });
        transaction.commit();
    }

    @Test
    public void testAgregateQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<MahasiswaSizeDto> createQuery = entityManager.createQuery(
            "select new com.orm.jpaibbernate.jpahibbernate.dtos.MahasiswaSizeDto(count(m.id)) from Mahasiswa as m", 
            MahasiswaSizeDto.class);
        MahasiswaSizeDto mahasiswaSizeDto = createQuery.getSingleResult();
        System.out.println("Jumlah mahasiswa : "+ mahasiswaSizeDto.getMahasiswaSize());
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testGrouping() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<ProdiDto> createQuery = entityManager.createQuery(
            "select new com.orm.jpaibbernate.jpahibbernate.dtos.ProdiDto(count(p.name), p.name) from Mahasiswa as m inner join m.prodi as p group by p.name", 
            ProdiDto.class);
        List<ProdiDto> resultList = createQuery.getResultList();
        resultList.forEach(m -> {
            System.out.println("prodi " + m.getNamaPodi()+" jumlah mahasiwa "+m.getJumlah_mahasiswa());
        });
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testNativeQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNativeQuery(
            "SELECT * FROM mahasiswa", Mahasiswa.class);
        List<Mahasiswa> resultList = query.getResultList();
        resultList.forEach(m -> {
            System.out.println("nama : "+ m.getName()+"\n");
        });
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testNamedNativeQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Mahasiswa> namedQuery = entityManager.createNamedQuery("native.mahasiswa.findAll", Mahasiswa.class);
        List<Mahasiswa> mahasiwaList = namedQuery.getResultList();
        mahasiwaList.forEach(m -> {
            System.out.println("nama : "+ m.getName());
        });
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testCriteriaQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Mahasiswa> criteriaQuery = criteriaBuilder.createQuery(Mahasiswa.class);
        Root<Mahasiswa> root = criteriaQuery.from(Mahasiswa.class);
        criteriaQuery.select(root);
        
        TypedQuery<Mahasiswa> createQuery2 = entityManager.createQuery(criteriaQuery);
        List<Mahasiswa> resultList = createQuery2.getResultList();
        resultList.forEach(m -> {
            System.out.println("nama : "+ m.getName());
        });
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testCriteriaSelectNonEntity() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        // kita buat criteraBuilder terlebih dahulu
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Disni kita tentukan return value dari query select kita nanti, berhubung
        // kita tidak terlalu memperdulikanya kita bisa isikan Object array
        CriteriaQuery<Object[]> criteria = criteriaBuilder.createQuery(Object[].class);
        // disini kita definisikan Enitty mana yang kita ingin select, misalnya disini kita ingin 
        // melakukan select ke entity Mahasiswa
        Root<Mahasiswa> root = criteria.from(Mahasiswa.class);
        // disini kita definisikan kolom dari entity yang ingin kita select
        criteria.select(criteriaBuilder.array(root.get("name"), root.get("email")));

        TypedQuery<Object[]> createQuery = entityManager.createQuery(criteria);
        List<Object[]> resultList = createQuery.getResultList();
        for (Object[] mahasiswa : resultList) {
            System.out.println("Name :" + mahasiswa[0]);
            System.out.println("Email :" + mahasiswa[1]);
        }
        transaction.commit();
    }

    @Test
    public void testCriteriaSelectNonEntity2() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MahasiswaNameEmailDto> criteriaQuery = builder.createQuery(MahasiswaNameEmailDto.class);
        Root<Mahasiswa> root = criteriaQuery.from(Mahasiswa.class);
        criteriaQuery.select(builder.construct(MahasiswaNameEmailDto.class, root.get("name"), root.get("email")));

        TypedQuery<MahasiswaNameEmailDto> query = entityManager.createQuery(criteriaQuery);
        List<MahasiswaNameEmailDto> mahasiswaList = query.getResultList();
        mahasiswaList.forEach(m -> {
            System.out.println("Name : " + m.getName());
            System.out.println("Email : " + m.getEmail());
        });
        transaction.commit();
    }

    @Test
    public void testCriteriaQueryWhereClasuse() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MahasiswaNameEmailDto> criteria = builder.createQuery(MahasiswaNameEmailDto.class);
        Root<Mahasiswa> mahasiswaRoot = criteria.from(Mahasiswa.class);
        criteria.select(builder.construct(MahasiswaNameEmailDto.class, mahasiswaRoot.get("name"), mahasiswaRoot.get("email")));
        criteria.where(
            builder.equal(mahasiswaRoot.get("name"), "Abdillah Alli"),
            builder.equal(mahasiswaRoot.get("email"), "lian@gmail.com")
        );

        TypedQuery<MahasiswaNameEmailDto> createQuery = entityManager.createQuery(criteria);
        List<MahasiswaNameEmailDto> mahasiswaList = createQuery.getResultList();
        mahasiswaList.forEach(m -> {
            System.out.println("Name : "+ m.getName());
            System.out.println("Email : "+m.getEmail());
        });
        transaction.commit();
    }

    @Test
    public void testCriteriaQueryWhereClasuseWithOR() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MahasiswaNameEmailDto> criteria = builder.createQuery(MahasiswaNameEmailDto.class);
        Root<Mahasiswa> mahasiswaRoot = criteria.from(Mahasiswa.class);
        criteria.select(builder.construct(MahasiswaNameEmailDto.class, mahasiswaRoot.get("name"), mahasiswaRoot.get("email")));
        criteria.where(
            builder.or(
                builder.equal(mahasiswaRoot.get("name"), "Abdillah Alli"),
                builder.equal(mahasiswaRoot.get("email"), "lian@gmail.com")
            )
        );

        TypedQuery<MahasiswaNameEmailDto> createQuery = entityManager.createQuery(criteria);
        List<MahasiswaNameEmailDto> mahasiswaList = createQuery.getResultList();
        mahasiswaList.forEach(m -> {
            System.out.println("Name : "+ m.getName());
            System.out.println("Email : "+m.getEmail());
        });
        transaction.commit();
    }

    @Test
    public void testCriteriaJoinClause() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> procudtRoot = criteria.from(Product.class);
        // melakukan join
        Join<Product, Brand> brandRoot = procudtRoot.join("brand"); // brand disini merupakan kolom foreign key
        criteria.select(procudtRoot);
        criteria.where(
            builder.or(
                builder.equal(procudtRoot.get("name"), "POCO X3 NFC"),
                builder.equal(brandRoot.get("name"), "POCO")
            )
        );
        TypedQuery<Product> products = entityManager.createQuery(criteria);
        List<Product> productList = products.getResultList();
        productList.forEach(p -> {
            System.out.println("Brand : "+p.getName());
            System.out.println("Product : "+p.getBrand().getName());
        });
        transaction.commit();
    }

    @Test
    public void testParameterExpression() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> rootProduct = criteria.from(Product.class);
        // membuat parameter
        ParameterExpression<String> productNameParameter = builder.parameter(String.class, "productName");

        criteria.select(rootProduct);
        criteria.where(
            builder.equal(rootProduct.get("name"), productNameParameter)
        );

        TypedQuery<Product> productQuery = entityManager.createQuery(criteria);
        productQuery.setParameter(productNameParameter, "POCO X3 NFC");
        List<Product> productList = productQuery.getResultList();
        productList.forEach(p -> {
            System.out.println("Product name : "+ p.getName());
            System.out.println("Product Desc : "+ p.getDescription());
        });

        transaction.commit();
    }

    @Test
    public void testCriteriaGroupBy() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PriceListDto> criteria = builder.createQuery(PriceListDto.class);
        Root<Product> rootProduct = criteria.from(Product.class);
        criteria.select(
            builder.construct(PriceListDto.class, 
            // agregaate function
            builder.min(rootProduct.get("price")),
            builder.max(rootProduct.get("price")),
            builder.avg(rootProduct.get("price")))
        );

        // melakukan grouping berdasarka id
        criteria.groupBy(rootProduct.get("id"));
        // menggunakan keyword having untuk melakukan identifier(ini sama kek where)
        criteria.having(builder.lessThan(builder.min(rootProduct.get("price")), 4000000L));

        TypedQuery<PriceListDto> priceListDto = entityManager.createQuery(criteria);
        List<PriceListDto> priceList = priceListDto.getResultList();
        priceList.forEach(p -> {
            System.out.println("max price : " + p.getMax());
            System.out.println("min price: " + p.getMin());
            System.out.println("avrage price : " + p.getAvrage());
        });

        transaction.commit();
    }

    @Test
    public void testCriteriaNonQuery() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Mahasiswa> criteria = builder.createCriteriaUpdate(Mahasiswa.class);
        Root<Mahasiswa> mahasiswaRoot = criteria.from(Mahasiswa.class);
        
        criteria.set(mahasiswaRoot.get("name"), "alliano@gmail.com");

        criteria.where(
            builder.equal(mahasiswaRoot.get("id"), 3)
        );

        Query query = entityManager.createQuery(criteria);
        int executeUpdate = query.executeUpdate();
        System.out.println("Success update "+executeUpdate+" record");

        transaction.commit();
    }
}
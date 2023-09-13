package com.orm.jpaibbernate.jpahibbernate.entities;

import com.orm.jpaibbernate.jpahibbernate.entities.embededs.Contact;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Table(name = "sellers")
@Entity @Setter @Getter
public class Seller {

    @Id
    private String id;

    @Column(name = "store_name")
    private String storeName;

    @Embedded
    private Contact contact;

    @OneToOne(mappedBy = "seller")
    private Store store;
}

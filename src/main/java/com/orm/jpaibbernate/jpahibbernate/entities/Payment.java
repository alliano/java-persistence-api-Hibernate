package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity @Table(name = "payment")
public class Payment {
    
    @Id
    private String id;

    private Long amount;
}

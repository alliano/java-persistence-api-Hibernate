package com.orm.jpaibbernate.jpahibbernate.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "transaction")
public class Transaction {

    @Id
    private String id;

    private Long belance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
}

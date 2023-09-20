package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorColumn(name = "type_of_student")
@DiscriminatorValue(value = "STUDENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "students")
public class Student {
    
    @Id
    private String id;

    private String name;

    private String npm;

    private double ipk;
}

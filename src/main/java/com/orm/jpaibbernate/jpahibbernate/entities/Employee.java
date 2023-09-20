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

@NoArgsConstructor @Setter @Getter
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity @Table(name = "employees")
public class Employee {
    
    @Id 
    private String id;

    private String name;
}

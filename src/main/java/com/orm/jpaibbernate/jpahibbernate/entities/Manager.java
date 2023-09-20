package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue(value = "MANAGER")
@Entity @Setter @Getter @NoArgsConstructor
public class Manager extends Employee {
    
    @Column(name = "total_manager")
    private Integer totalManager;
}

package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @DiscriminatorValue(value = "VICE_PRECINDENT")
@Entity @NoArgsConstructor
public class VicePresident extends Employee {
    
    @Column(name = "total_vice_president")
    private Integer totalVicePrecident;
}

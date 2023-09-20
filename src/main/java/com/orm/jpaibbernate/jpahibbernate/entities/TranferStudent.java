package com.orm.jpaibbernate.jpahibbernate.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@DiscriminatorValue(value = "TRANFER_STUDENT")
@Entity @NoArgsConstructor 
public class TranferStudent extends Student {
    
    @Column(name = "from_university")
    private String fromUniversity;

    @Column(name = "moving_date")
    private Date movingDate;
    
}

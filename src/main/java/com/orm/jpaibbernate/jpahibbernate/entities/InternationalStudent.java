package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue(value = "INTERNATIONAL_STUDENT")
@Entity @NoArgsConstructor @Setter @Getter
public class InternationalStudent extends Student {
    
    @Column(name = "from_country")
    private String fromCountry;

    private String visa;
}

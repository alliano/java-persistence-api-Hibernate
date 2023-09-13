package com.orm.jpaibbernate.jpahibbernate.entities;

import com.orm.jpaibbernate.jpahibbernate.entities.embededs.DepatementId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "departements")
public class Departement {
    
    @EmbeddedId
    private DepatementId depatementId;

    @Column(name = "name")
    private String name;
}

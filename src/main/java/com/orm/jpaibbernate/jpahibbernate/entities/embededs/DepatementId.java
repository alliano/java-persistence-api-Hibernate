package com.orm.jpaibbernate.jpahibbernate.entities.embededs;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Embeddable @Setter @Getter
public class DepatementId implements Serializable {
    
    @Column(name = "departement_id")
    private String departementId;

    @Column(name = "company_id")
    private String companyId;
}

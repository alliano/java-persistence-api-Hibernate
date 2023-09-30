package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "dosen")
public class Dosen extends AuditBaseEntity<String> {

    private String name;

    private String email;

    /**
     * Pada atribut version, kita harus menambahkan annotation
     * @Version, agar jpa dapat mengetahui jikalau data pada kolom
     * version telah berubah.
     * 
     * dan jikalau data pada kolom version berubah maka, transaction
     * lainya akan di rollback
     */
    @Version
    private Long version;  
}

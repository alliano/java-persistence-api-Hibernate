package com.orm.jpaibbernate.jpahibbernate.entities;

import java.sql.Blob;
import java.sql.Clob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    /**
     * Dengan kita meng annotasi attribut dengan @Transient
     * maka atribut tersebut tidak akan di hiraukan saat melakukan operasi ke database
     * seolah-olah kolom tersebut tidak ada di database
     */
    @Transient
    private String fullName;
  
    @Column(name = "picture")
    private Blob picture;

    @Column(name = "description")
    private Clob description;

}

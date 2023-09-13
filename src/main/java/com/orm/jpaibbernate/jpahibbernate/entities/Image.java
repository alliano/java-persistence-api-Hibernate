package com.orm.jpaibbernate.jpahibbernate.entities;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "images")
public class Image {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    private String description;

    @Lob
    private byte[] image;

    private Blob a;
}

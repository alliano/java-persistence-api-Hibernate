package com.orm.jpaibbernate.jpahibbernate.entities;

import java.time.LocalDateTime;
import java.util.Calendar;

import com.orm.jpaibbernate.jpahibbernate.entities.entityListeners.CreatedAtListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Table(name = "categories")
@Entity @Setter @Getter @EntityListeners(value = {
    CreatedAtListener.class
})
public class Category implements CreatedAtAware {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    private String name;

    private String description;

    /**
     * Disini kita perlu memberikan annotasi @Temporal
     * untuk memberi tau JPA bahwa data ini akan di mappring
     * menjadi tipe data apa(dalam kasus ini kita akan mapping menjadi TIMESTAMP)
     * dengan begitu Database tidak akan bingung
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Calendar updatedAt;

    /**
     * pada attribut ini kita tidak perlu menambahkan annotasi
     * @Temporal, karena secara otomatis JPA mengetahui akan di mapping
     * menjadi tipe data apa(Timestamp)
     * karena LocalDateTime dari padkage java.time
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

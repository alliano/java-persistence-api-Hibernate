package com.orm.jpaibbernate.jpahibbernate.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity @Table(name = "matakuliah")
public class MataKuliah {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany()
    @JoinTable(
        name = "mahasiswa_matakuliah",
        joinColumns = @JoinColumn(name = "matakuliah_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id")
        )
    private List<Mahasiswa> mahasiswa;
}

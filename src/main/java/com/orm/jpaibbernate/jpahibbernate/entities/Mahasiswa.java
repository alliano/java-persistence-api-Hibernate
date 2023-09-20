package com.orm.jpaibbernate.jpahibbernate.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor 
@Entity @Table(name = "mahasiswa")
public class Mahasiswa {
    
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;
    /**
     * Defaut strategy fetch pada annotation @OneToOne adalah EAGER
     * artinya jikalau ada query data mahasiswa maka data pada tabel 
     * refrence nya akan ikut di query menggunakan JOIN,
     * Jikalau kita mengubah strategy nya menjadi LAZY maka data pada tabel
     * refrence nya tidak ikut di query. 
     * 
     * Namun saat data pada tabel reference nya di butuhkan maka
     * Jpa akan melakukan query lagi secara terpisah(tidak menggunakan JOIN)
     */
    @OneToOne(mappedBy = "mahasiswa", fetch = FetchType.LAZY)
    private Prodi prodi;

    /**
     * Default strategy fetch pada annotation @ManyToMany adalah LAZY.  
     * Saat kita mengubah strategy pengambilan data pada tabel reference nya
     * pada annotation @ManyToMany menjadi EAGER, maka saat terjadi proses
     * query data yang ada pada kolom refrence nya akan ikut di query
     * menggunakan JOIN
     */
    @ManyToMany(mappedBy = "mahasiswa", fetch = FetchType.LAZY)
    private List<MataKuliah> mataKuliah;
}

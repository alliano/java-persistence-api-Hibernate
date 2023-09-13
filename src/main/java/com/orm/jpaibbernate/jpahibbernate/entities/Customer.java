package com.orm.jpaibbernate.jpahibbernate.entities;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Table(name = "customers")
@Entity @Setter @Getter
public class Customer {
    
    @Id @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "primary_email")
    private String primaryEmail;
    
    @Column(name = "meried")
    private Boolean meried; 

    // disini kita menggunakan Byte karena byte merepresentasiman nilai yang kecil
    @Column(name = "age")
    private Byte age; 

    /**
     * pada atribut type ini kita beritahu bahagaimana cara menyimpan/mapping
     * data tersebut kedalam database denga menggunakan annotasi 
     * @Enumerated dengan menggunakan Strategy STRING
     */
    @Enumerated(value = EnumType.STRING)
    private CustomerType type;

    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    @Column(name = "name")
    private List<String> hobbies;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    /**
     * @MapKeyColumn digunakan untuk memberi tau JPA Key dari atribut ini disimpan pada column apa
     * Misalnya di contoh kali ini disimpan pada tabel skills kolom name
     */
    @MapKeyColumn(name = "name") 
    /**
     * @Column disini digunakan untuk memberi tau JPA bahwa value dari attribut Map ini disimpan pada kolom mana
     * dalam kasus ini value nya disimpan pada tabel skills column value
     */
    @Column(name = "value")
    private Map<String, Integer> skills;
}

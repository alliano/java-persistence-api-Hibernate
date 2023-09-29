package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "posts")
@Setter @Getter @NoArgsConstructor
public class Post extends AuditBaseEntity<String> {
    
    private String name;

    private String title;

    private String content;
}

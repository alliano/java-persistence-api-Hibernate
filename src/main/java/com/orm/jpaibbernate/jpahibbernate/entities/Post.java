package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries(value = {
    @NamedQuery(name = "Post.findByTitle", query = "select p from Post as p where p.title = :title"),
    @NamedQuery(name = "Post.findAll", query = "select p from Post as p order by p.name desc")
})
@Entity @Table(name = "posts")
@Setter @Getter @NoArgsConstructor
public class Post extends AuditBaseEntity<String> {
    
    private String name;

    private String title;

    private String content;
}

package com.orm.jpaibbernate.jpahibbernate.entities;

import java.time.LocalDateTime;
import com.orm.jpaibbernate.jpahibbernate.entities.entityListeners.CreatedAndUpdatedListener;
import com.orm.jpaibbernate.jpahibbernate.entities.entityUtils.CreateAndUpdateAware;
import com.orm.jpaibbernate.jpahibbernate.entities.entityUtils.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Table(name = "users")
@Entity @Setter @Getter 
@EntityListeners(value = {
    CreatedAndUpdatedListener.class
})
public class User implements CreateAndUpdateAware {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Transient
    private String fullName;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "created_at")
private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user")
    private Ewallet ewallet;

    @PostLoad
    public void setFullName() {
        this.fullName = "Mr.".concat(this.name).concat(" S.kom");
    }
}

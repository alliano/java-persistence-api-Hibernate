package com.orm.jpaibbernate.jpahibbernate.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@MappedSuperclass
public abstract class AuditBaseEntity<T extends Serializable> {
    
    @Id
    private T id;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
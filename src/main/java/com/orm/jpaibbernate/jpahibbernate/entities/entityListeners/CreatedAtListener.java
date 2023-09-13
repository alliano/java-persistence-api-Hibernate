package com.orm.jpaibbernate.jpahibbernate.entities.entityListeners;

import java.time.LocalDateTime;

import com.orm.jpaibbernate.jpahibbernate.entities.CreatedAtAware;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreatedAtListener {
    
    @PreUpdate
    @PrePersist
    public void setCreatedAt(CreatedAtAware objAtAware){
        objAtAware.setCreatedAt(LocalDateTime.now());
    }
}

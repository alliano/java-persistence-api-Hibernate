package com.orm.jpaibbernate.jpahibbernate.entities.entityListeners;

import java.time.LocalDateTime;
import com.orm.jpaibbernate.jpahibbernate.entities.entityUtils.CreateAndUpdateAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreatedAndUpdatedListener {
    
    @PrePersist
    public void setCreatedAt(CreateAndUpdateAware createAndUpdateAware) {
        createAndUpdateAware.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdatedAt(CreateAndUpdateAware createAndUpdateAware) {
        createAndUpdateAware.setUpdatedAt(LocalDateTime.now());
    }
}

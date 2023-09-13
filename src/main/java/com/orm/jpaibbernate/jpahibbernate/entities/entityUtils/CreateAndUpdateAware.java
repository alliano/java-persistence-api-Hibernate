package com.orm.jpaibbernate.jpahibbernate.entities.entityUtils;

import java.time.LocalDateTime;

public interface CreateAndUpdateAware {
    
    public void setCreatedAt(LocalDateTime localDateTime);

    public void setUpdatedAt(LocalDateTime localDateTime);
}

package com.orm.jpaibbernate.jpahibbernate.entities;

import java.time.LocalDateTime;

public interface CreatedAtAware {
    
    public void setCreatedAt(LocalDateTime localDateTime);
}

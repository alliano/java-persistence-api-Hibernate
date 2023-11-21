package com.orm.jpaibbernate.jpahibbernate.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter @AllArgsConstructor
public class PriceListDto {
    
    private Object min;

    private Object max;

    private Object avrage;
}

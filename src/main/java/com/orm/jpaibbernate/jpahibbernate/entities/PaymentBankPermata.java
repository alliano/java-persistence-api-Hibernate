package com.orm.jpaibbernate.jpahibbernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
@Entity @Table(name = "payment_bank_permata")
public class PaymentBankPermata extends Payment {
    
    @Column(name = "card_number")
    private String cardNumber;
}

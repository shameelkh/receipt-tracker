package com.shameelkhan.receipts.tracker.service.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lineItemId;

    @Column
    private String title;

    @Column
    private BigDecimal price;
}

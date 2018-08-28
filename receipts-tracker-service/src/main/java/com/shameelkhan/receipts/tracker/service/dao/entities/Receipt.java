package com.shameelkhan.receipts.tracker.service.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receiptId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name = "merchant_address_id")
    private MerchantAddress merchantAddress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptLineItem> lineItems;

    @Column
    private LocalDate date;

    @Column
    private BigDecimal total;
}

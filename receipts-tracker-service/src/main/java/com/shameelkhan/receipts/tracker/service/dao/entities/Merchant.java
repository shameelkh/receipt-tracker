package com.shameelkhan.receipts.tracker.service.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchantId;

    @Column
    private String name;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private Set<MerchantAddress> addresses;
}

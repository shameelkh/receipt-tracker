package com.shameelkhan.receipts.tracker.service.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"merchant"})
@ToString(exclude = {"merchant"})
public class MerchantAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchantAddressId;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    Merchant merchant;

    @Column
    private String streetNumber;

    @Column
    private String street;

    @Column
    private String city;

}

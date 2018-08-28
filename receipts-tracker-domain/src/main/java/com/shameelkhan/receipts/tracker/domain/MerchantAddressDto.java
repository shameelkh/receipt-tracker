package com.shameelkhan.receipts.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantAddressDto {

    private Long merchantAddressId;

    private String streetNumber;

    private String street;

    private String city;
}

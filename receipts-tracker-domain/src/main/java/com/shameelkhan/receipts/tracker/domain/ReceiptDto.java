package com.shameelkhan.receipts.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto {

    private Long receiptId;

    private Long userId;

    private Long bankId;

    private MerchantDto merchant;

    private MerchantAddressDto merchantAddress;

    private List<ReceiptLineItemDto> lineItems;

    private LocalDate date;

    private BigDecimal total;
}

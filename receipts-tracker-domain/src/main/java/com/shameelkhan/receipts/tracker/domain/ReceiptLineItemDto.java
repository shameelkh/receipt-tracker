package com.shameelkhan.receipts.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptLineItemDto {

    public Long lineItemId;

    public String title;

    public BigDecimal price;
}

package com.shameelkhan.receipts.tracker.service.mappers;

import com.shameelkhan.receipts.tracker.domain.MerchantDto;
import com.shameelkhan.receipts.tracker.domain.ReceiptDto;
import com.shameelkhan.receipts.tracker.service.dao.entities.Merchant;
import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MerchantMapper extends DefaultMapper<MerchantDto, Merchant> {

    @Autowired
    public MerchantMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @Override
    protected Class<Merchant> dbClass() {
        return Merchant.class;
    }

    @Override
    protected Class<MerchantDto> domainClass() {
        return MerchantDto.class;
    }
}

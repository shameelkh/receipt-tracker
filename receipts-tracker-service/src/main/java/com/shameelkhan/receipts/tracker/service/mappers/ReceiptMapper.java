package com.shameelkhan.receipts.tracker.service.mappers;

import com.shameelkhan.receipts.tracker.domain.ReceiptDto;
import com.shameelkhan.receipts.tracker.service.dao.entities.Bank;
import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import com.shameelkhan.receipts.tracker.service.dao.entities.User;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ReceiptMapper extends DefaultMapper<ReceiptDto, Receipt> {

    @Autowired
    public ReceiptMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    protected Class<Receipt> dbClass() {
        return Receipt.class;
    }

    @Override
    protected Class<ReceiptDto> domainClass() {
        return ReceiptDto.class;
    }

    @Override
    protected void configureMapperFactory() {
        getDefaultMapperFactory().classMap(dbClass(), domainClass())
                .field("user", "userId")
                .field("bank", "bankId")
                .byDefault().register();

        getDefaultMapperFactory().getConverterFactory()
                .registerConverter(new CustomConverter<User, Long>() {

                    @Override
                    public Long convert(User user, Type<? extends Long> type,
                                        MappingContext mappingContext) {
                        return user.getUserId();
                    }
                });

        getDefaultMapperFactory().getConverterFactory()
                .registerConverter(new CustomConverter<Long, User>() {
                    @Override
                    public User convert(Long userId, Type<? extends User> type,
                                        MappingContext mappingContext) {
                        User user = new User();
                        user.setUserId(userId);
                        return user;
                    }
                });

        getDefaultMapperFactory().getConverterFactory()
                .registerConverter(new CustomConverter<Bank, Long>() {

                    @Override
                    public Long convert(Bank bank, Type<? extends Long> type,
                                        MappingContext mappingContext) {
                        return bank.getBankId();
                    }
                });


        getDefaultMapperFactory().getConverterFactory()
                .registerConverter(new CustomConverter<Long, Bank>() {
                    @Override
                    public Bank convert(Long bankId, Type<? extends Bank> type,
                                        MappingContext mappingContext) {
                        Bank bank = new Bank();
                        bank.setBankId(bankId);
                        return bank;
                    }
                });
    }
}

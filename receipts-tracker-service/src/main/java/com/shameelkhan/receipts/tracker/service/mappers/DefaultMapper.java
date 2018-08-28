package com.shameelkhan.receipts.tracker.service.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultMapper<DomainClass, DatabaseClass> {

    private final MapperFactory defaultMapperFactory;

    @Autowired
    public DefaultMapper(MapperFactory mapperFactory) {
        this.defaultMapperFactory = mapperFactory;
    }

    public DomainClass mapDbToDomain(DatabaseClass dbObject) {
        return getMapperFacade().map(dbObject, domainClass());
    }

    public DatabaseClass mapDomainToDb(DomainClass domainObject) {
        return getMapperFacade().map(domainObject, dbClass());
    }

    public List<DomainClass> mapDbToDomain(List<DatabaseClass> dbObjects) {
        List<DomainClass> domainObjects = new ArrayList<>();

        for(DatabaseClass dbObject : dbObjects) {
            domainObjects.add(mapDbToDomain(dbObject));
        }

        return domainObjects;
    }

    protected void configureMapperFactory() {
        getDefaultMapperFactory().classMap(dbClass(), domainClass()).byDefault().register();
    }

    protected abstract Class<DatabaseClass> dbClass();

    protected abstract Class<DomainClass> domainClass();

    protected MapperFactory getDefaultMapperFactory() {
        return defaultMapperFactory;
    }

    private MapperFacade getMapperFacade() {
        return getDefaultMapperFactory().getMapperFacade();
    }
}

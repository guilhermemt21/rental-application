package com.rental.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.rental.server.core.property.PropertyDAO;
import org.jdbi.v3.core.Jdbi;

public class DAODependencyModule extends AbstractModule {

    private final Jdbi applicationJdbi;

    public DAODependencyModule(Jdbi applicationJdbi) {
        this.applicationJdbi = applicationJdbi;
    }

    @Override
    protected void configure() {
    }


    @Provides
    Jdbi jdbi() {
        return applicationJdbi;
    }

    @Singleton
    @Provides
    PropertyDAO propertyDAO() {
        return applicationJdbi.onDemand(PropertyDAO.class);
    }

}

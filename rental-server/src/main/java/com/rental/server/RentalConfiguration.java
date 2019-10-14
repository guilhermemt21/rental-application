package com.rental.server;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;


class RentalConfiguration extends Configuration {

    @Getter
    private DataSourceFactory applicationDataSourceFactory = new DataSourceFactory();

}

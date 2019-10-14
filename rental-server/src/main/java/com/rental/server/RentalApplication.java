package com.rental.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rental.server.health.TemplateHealthCheck;
import com.rental.server.resources.PropertyResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

import static org.eclipse.jetty.servlets.CrossOriginFilter.*;

public class RentalApplication extends Application<RentalConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RentalApplication().run(args);
    }

    @Override
    public String getName() {
        return "Rental Server Parent Module";
    }

    @Override
    public void initialize(final Bootstrap<RentalConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<RentalConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(RentalConfiguration configuration) {
                return configuration.getApplicationDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final RentalConfiguration configuration, final Environment environment) {
        JdbiFactory factory = new JdbiFactory();
        Jdbi applicationJdbi = factory.build(environment, configuration.getApplicationDataSourceFactory(), "applicationDatabase");

        Injector injector = Guice.createInjector(new DAODependencyModule(applicationJdbi));

        injector.injectMembers(applicationJdbi);

        registerHealthChecks(environment);
        registerResources(injector, environment);

        FilterRegistration.Dynamic cors = environment.servlets()
                .addFilter("CORSFilter", CrossOriginFilter.class);

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, environment.getApplicationContext().getContextPath() + "*");
        cors.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,HEAD,OPTIONS");
        cors.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(ALLOWED_HEADERS_PARAM, "*");
        cors.setInitParameter(EXPOSED_HEADERS_PARAM, "Link");
        cors.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
    }

    private void registerResources(Injector injector, Environment environment) {
        environment.jersey().register(injector.getInstance(PropertyResource.class));
    }

    private void registerHealthChecks(Environment environment) {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("template", healthCheck);
    }

}

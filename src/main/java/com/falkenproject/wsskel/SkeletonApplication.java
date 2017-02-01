package com.falkenproject.wsskel;

import com.falkenproject.wsskel.dao.SkeletonDAO;
import com.falkenproject.wsskel.web.SkeletonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class SkeletonApplication extends Application<SkeletonApplicationConfiguration> {

    @Override
    public void initialize(Bootstrap<SkeletonApplicationConfiguration> bootstrap) {
        bootstrap
                .addBundle(new MigrationsBundle<SkeletonApplicationConfiguration>() {
                    @Override
                    public DataSourceFactory getDataSourceFactory(SkeletonApplicationConfiguration configuration) {
                        return configuration.getDataSourceFactory();
                    }
                });
    }

    @Override
    public void run(SkeletonApplicationConfiguration config, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jDbi = factory.build(environment, config.getDataSourceFactory(), "skeleton");

        environment.jersey().register(new SkeletonResource(jDbi.onDemand(SkeletonDAO.class)));
    }
}

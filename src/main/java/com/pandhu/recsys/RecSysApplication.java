package com.pandhu.recsys;


import com.pandhu.recsys.core.Category;
import com.pandhu.recsys.core.Leaderboard;
import com.pandhu.recsys.db.CategoryDAO;
import com.pandhu.recsys.db.LeaderboardDAO;
import com.pandhu.recsys.resources.CategoryResource;
import com.pandhu.recsys.resources.LeaderboardResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RecSysApplication extends Application<RecSysConfiguration> {
    public static void main(String[] args) throws Exception {
        new RecSysApplication().run(args);
    }

    private final HibernateBundle<RecSysConfiguration> hibernateBundle =
            new HibernateBundle<RecSysConfiguration>(Leaderboard.class, Category.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(RecSysConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RecSysConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );


        bootstrap.addBundle(new MigrationsBundle<RecSysConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(RecSysConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(RecSysConfiguration configuration, Environment environment) {
        final LeaderboardDAO leaderboardDAO = new LeaderboardDAO(hibernateBundle.getSessionFactory());
        final CategoryDAO categoryDAO = new CategoryDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new LeaderboardResource(leaderboardDAO));
        environment.jersey().register(new CategoryResource(categoryDAO));
    }
}

package com.mailserver.manager;

import com.mailserver.manager.db.MailVirtualAliases;
import com.mailserver.manager.db.MailVirtualDomains;
import com.mailserver.manager.db.MailVirtualUsers;
import com.mailserver.manager.db.dao.MailDomainDao;
import com.mailserver.manager.db.dao.MailUserDao;
import com.mailserver.manager.resources.DomainMgmtResource;
import com.mailserver.manager.resources.UserMgmtResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MailboxManagerApplication extends Application<MailboxManagerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MailboxManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "MailboxManager";
    }

    @Override
    public void initialize(final Bootstrap<MailboxManagerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final MailboxManagerConfiguration configuration,
                    final Environment environment) {
        final MailUserDao mailUserDao = new MailUserDao(hibernate.getSessionFactory());
        final MailDomainDao mailDomainDao = new MailDomainDao(hibernate.getSessionFactory());
        final UserMgmtResource userMgmtResource = new UserMgmtResource(mailUserDao, mailDomainDao);
        final DomainMgmtResource domainMgmtResource = new DomainMgmtResource(mailDomainDao);
        environment.jersey().register(userMgmtResource);
        environment.jersey().register(domainMgmtResource);
    }

    private final HibernateBundle<MailboxManagerConfiguration> hibernate =
            new HibernateBundle<MailboxManagerConfiguration>(MailVirtualDomains.class, MailVirtualUsers.class,
                    MailVirtualAliases.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MailboxManagerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

}

package com.mailserver.manager.db.dao;

import com.mailserver.manager.db.MailVirtualDomains;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Created by Akhilesh on 8/8/16.
 */
public class MailDomainDao extends AbstractDAO<MailVirtualDomains> {
    public MailDomainDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public long create(final String aNewDomain) {
        return persist(new MailVirtualDomains(aNewDomain)).getId();
    }

    public MailVirtualDomains getDomainByName(final String aDomainName) {
        final Query query = namedQuery("MailVirtualDomains.getDomainByName");
        query.setParameter("name", aDomainName);
        return uniqueResult(query);
    }

    public void removeDomainByName(String domain) {
        final Query query = namedQuery("MailVirtualDomains.removeDomainByName");
        query.setParameter("name", domain);
        query.executeUpdate();
    }
}

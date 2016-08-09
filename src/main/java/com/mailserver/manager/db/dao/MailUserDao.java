package com.mailserver.manager.db.dao;

import com.mailserver.manager.db.MailVirtualUsers;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Akhilesh on 8/8/16.
 */
public class MailUserDao extends AbstractDAO<MailVirtualUsers> {

    public MailUserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public long create(MailVirtualUsers virtualUsers) {
        return persist(virtualUsers).getId();
    }

    public List<MailVirtualUsers> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    }
}

package com.mailserver.manager.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Akhilesh on 8/8/16.
 */
@Entity
@Table(name = "mail_virtual_domains")
@NamedQueries({
        @NamedQuery(
                name = "MailVirtualDomains.getDomainByName",
                query = "FROM MailVirtualDomains AS mvd WHERE mvd.name =:name"
        ),
        @NamedQuery(
                name = "MailVirtualDomains.removeDomainByName",
                query = "DELETE FROM MailVirtualDomains AS mvd WHERE mvd.name =:name"
        )
})
public class MailVirtualDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "domain_id")
    private long id;
    @Column
    @NotNull
    private String name;

    public MailVirtualDomains() {}

    public MailVirtualDomains(String domain) {
        this.name = domain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

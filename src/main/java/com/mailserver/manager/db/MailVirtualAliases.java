package com.mailserver.manager.db;

/**
 * Created by Akhilesh on 8/8/16.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * CREATE TABLE `mail_virtual_aliases` (
 id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 domain_id INT(11) NOT NULL,
 source VARCHAR(40) NOT NULL,
 destination VARCHAR(80) NOT NULL,
 FOREIGN KEY (domain_id) REFERENCES mail_virtual_domains(id) ON DELETE CASCADE
 ) ENGINE = InnoDB DEFAULT CHARSET=utf8;
 */
public class MailVirtualAliases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = MailVirtualDomains.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "domain_id")
    @NotNull
    private MailVirtualDomains mailDomain;
    @NotNull
    private String source;
    @NotNull
    private String destination;

    public MailVirtualAliases() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MailVirtualDomains getMailDomain() {
        return mailDomain;
    }

    public void setMailDomain(MailVirtualDomains mailDomain) {
        this.mailDomain = mailDomain;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

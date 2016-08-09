package com.mailserver.manager.db;

/**
 * Created by Akhilesh on 8/8/16.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * CREATE TABLE `mail_virtual_users` (
 id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 domainId INT(11) NOT NULL,
 user VARCHAR(40) NOT NULL,
 password VARCHAR(32) NOT NULL,
 CONSTRAINT UNIQUE_EMAIL UNIQUE (domainId,user),
 FOREIGN KEY (domainId) REFERENCES mail_virtual_domains(id) ON DELETE CASCADE) ENGINE = InnoDB DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name = "mail_virtual_users", uniqueConstraints = @UniqueConstraint(columnNames = {"domain_id", "user"}))
public class MailVirtualUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = MailVirtualDomains.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "domain_id")
    @NotNull
    private MailVirtualDomains mailDomain;
    @Column(name = "user")
    @NotNull
    private String user;
    @Column(name = "password")
    @NotNull
    private String password;

    public MailVirtualUsers() {}

    public MailVirtualUsers(String username, String password, MailVirtualDomains domainId) {
        this.user = username;
        this.password = password;
        this.mailDomain = domainId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MailVirtualDomains getMailDomain() {
        return mailDomain;
    }

    public void setMailDomain(MailVirtualDomains domainId) {
        this.mailDomain = domainId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

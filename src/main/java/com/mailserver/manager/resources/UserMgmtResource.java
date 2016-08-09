package com.mailserver.manager.resources;

import com.mailserver.manager.db.MailVirtualDomains;
import com.mailserver.manager.db.MailVirtualUsers;
import com.mailserver.manager.db.dao.MailDomainDao;
import com.mailserver.manager.db.dao.MailUserDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.mailserver.manager.Constants.*;

/**
 * Created by Akhilesh on 8/5/16.
 */
@Path("/" + DOMAIN_USER_RS_PATH)
@Consumes(MediaType.TEXT_PLAIN)
public class UserMgmtResource {

    private final MailUserDao mailUserDao;
    private final MailDomainDao mailDomainDao;

    public UserMgmtResource(MailUserDao mailUserDao, MailDomainDao mailDomainDao) {
        this.mailUserDao = mailUserDao;
        this.mailDomainDao = mailDomainDao;
    }

    @POST
    @UnitOfWork
    public Response addUser(@QueryParam(USERNAME) @NotNull String username,
                            @QueryParam(PASSWORD) @NotNull String password,
                            @PathParam(DOMAIN) @NotNull String domain) {

        MailVirtualDomains domainEntity = mailDomainDao.getDomainByName(domain);
        if (domainEntity != null) {
            mailUserDao.create(new MailVirtualUsers(username, password, domainEntity));
        } else {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}

package com.mailserver.manager.resources;

import com.mailserver.manager.Constants;
import com.mailserver.manager.db.dao.MailDomainDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.mailserver.manager.Constants.*;

/**
 * Created by Akhilesh on 8/9/16.
 */
@Path("/" + DOMAIN_RS_PATH)
@Consumes(MediaType.TEXT_PLAIN)
public class DomainMgmtResource {
    private final MailDomainDao mailDomainDao;

    public DomainMgmtResource(MailDomainDao mailDomainDao) {
        this.mailDomainDao = mailDomainDao;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addDomain(@QueryParam(ADDDOMAIN) @NotNull String domain) {
        mailDomainDao.create(domain);
        return Response.ok().build();
    }

    @DELETE
    @UnitOfWork
    public Response removeDomain(@QueryParam(REMOVEDOMAIN) @NotNull String domain) {
        mailDomainDao.removeDomainByName(domain);
        return Response.ok().build();
    }
}

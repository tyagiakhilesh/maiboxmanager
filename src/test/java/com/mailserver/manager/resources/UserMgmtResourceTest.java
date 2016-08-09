package com.mailserver.manager.resources;

import com.mailserver.manager.Constants;
import com.mailserver.manager.MailboxManagerApplication;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Akhilesh on 8/9/16.
 */
public class UserMgmtResourceTest {
    @BeforeClass
    public static void setup() throws Exception {
        new MailboxManagerApplication().run(new String[] {"server", "config.yml"});
    }
    @Test
    public void testDomain() {
        createClient(Constants.DOMAIN_RS_PATH, Constants.ADDDOMAIN, "aDummyDomain");
        createClient(Constants.DOMAIN_RS_PATH, Constants.REMOVEDOMAIN, "aDummyDomain");
    }

    @Test
    public void testUserRs() {
        createClient(Constants.DOMAIN_RS_PATH, Constants.ADDDOMAIN, "aDummyDomain");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("aDummyDomain").path("user");
        target = target.queryParam(Constants.USERNAME, "aUser");
        target = target.queryParam(Constants.PASSWORD, "aPass");
        final Invocation.Builder builder = target.request();
        Response response = builder.post(Entity.entity("", MediaType.TEXT_PLAIN), Response.class);
    }

    private void createClient(final String resourcePath, final String queryParamKey, final String queryParamValue) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path(resourcePath);
        target = target.queryParam(queryParamKey, queryParamValue);
        final Invocation.Builder builder = target.request();
        Response response = builder.post(Entity.entity("", MediaType.TEXT_PLAIN), Response.class);
    }
}

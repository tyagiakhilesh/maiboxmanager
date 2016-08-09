# MailboxManager

How to start the MailboxManager application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/mailboxmanager-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

What tech does it use?
---

This simple application uses dropwizard 1.0.0 to create web resources and then updating the 
database directly from the web resources. 

If you are looking that how to use the database in the dropwizard; how to create DAOs etc, 
then you are right place.!!



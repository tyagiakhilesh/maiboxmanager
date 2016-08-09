package com.mailserver.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Akhilesh on 8/8/16.
 */
public class MailboxManagerConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}


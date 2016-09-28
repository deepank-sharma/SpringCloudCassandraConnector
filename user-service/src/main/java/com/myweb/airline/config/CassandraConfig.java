package com.myweb.airline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	private CassandraConnection cassandraConnection;

	@Override
	protected String getKeyspaceName() {
		return cassandraConnection.getCassandraProperties().getKeyspaceName();
	}

	@Override
	protected String getContactPoints() {
		return cassandraConnection.getCassandraProperties().getContactPoints();
	}

	@Override
	protected int getPort() {
		return cassandraConnection.getCassandraProperties().getPort();
	}

	@Override
	protected AuthProvider getAuthProvider() {
		return new PlainTextAuthProvider(cassandraConnection.getCassandraProperties().getUsername(),
				cassandraConnection.getCassandraProperties().getPassword());
	}

}

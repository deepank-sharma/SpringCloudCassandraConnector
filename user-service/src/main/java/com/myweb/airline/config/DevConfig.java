package com.myweb.airline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DevConfig implements CassandraConnection{
	@Autowired
	private CassandraProperties properties;

	@Override
	public CassandraProperties getCassandraProperties() {
		return properties;
	}
}

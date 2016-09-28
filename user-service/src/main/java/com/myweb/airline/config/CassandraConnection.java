package com.myweb.airline.config;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;

public interface CassandraConnection {
	public CassandraProperties getCassandraProperties();
}

package com.myweb.airline.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Sharma D Create a custom service in CF :: 
 *         cf cups Cassandra_Airline -p
 *         "{\"node_ips\": [\"Your list of ips\"], \"thrift_port\": 9160, \"cql_port\": 9042, \"keyspace_name\": \"airline_account\"
 *         ,\"username\": \"airline_user\", \"password\": \"airline_password\"}"
 *
 */
@Profile("cloud")
@Configuration
public class CloudConfig extends AbstractCloudConfig implements CassandraConnection {
	@Autowired
	private CassandraProperties cassandraProperties;
	@Resource
	private List<Map<String, Object>> vCapList;

	@Override
	public CassandraProperties getCassandraProperties() {
		return setCassandraProperties(cassandraProperties);
	}

	@SuppressWarnings("unchecked")
	public CassandraProperties setCassandraProperties(CassandraProperties properties) {
		Map<String, Object> cMap = (Map<String, Object>) getService(System.getenv("cassandra_service"))
				.get("credentials");
		ArrayList<String> nodeIps = (ArrayList<String>) cMap.get("node_ips");
		properties.setContactPoints(String.join(",", nodeIps));
		properties.setPort((Integer) cMap.get("cql_port"));
		properties.setKeyspaceName((String) cMap.get("keyspace_name"));
		properties.setUsername((String) cMap.get("username"));
		properties.setPassword((String) cMap.get("password"));
		return properties;
	}

	private Map<String, Object> getService(String serviceName) {
		for (Map<String, Object> map : vCapList) {
			if (map.get("name").equals(serviceName)) {
				return map;
			}
		}
		return Collections.emptyMap();
	}

}

package com.myweb.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.myweb.airline.vo.User;

@RestController
@RequestMapping("/v1/users")
public class UserServiceController {
	@Autowired
	private CassandraOperations operations;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createUser(@RequestBody User user) {
		operations.insert(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		Select select = QueryBuilder.select().from("users");
		Clause clause = QueryBuilder.eq("id", id);
		select.where(clause);
		return operations.selectOne(select, User.class);
	}
}

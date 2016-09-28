package com.myweb.airline.vo;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {
	@JsonProperty("id")
	@PrimaryKeyColumn(name="id",ordinal=0,type=PrimaryKeyType.PARTITIONED)
	private String id;
	@JsonProperty("f_name")
	@Column(value="first_name")
	private String firstName;
	@JsonProperty("l_name")
	@Column(value="last_name")
	private String lastName;
	@JsonProperty("email")
	@Column(value="email")
	private String email;
}

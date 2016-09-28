package com.myweb.airline.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JToken {
	private String UUID;
	private String flightNumber;
	private Date expiry;
}

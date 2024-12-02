package com.auctionapp.web.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ensar Horozović
 */

@Getter
@Setter
public class LoginResponse {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private String city;

}

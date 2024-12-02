package com.auctionapp.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a User in the system.
 *
 * @author Ensar Horozović
 */

@Getter
@Setter
public class User {

	private Integer id;
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private Integer photo;
	private String city;
}

package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Represents a user in the system.
 * This entity is mapped to the "users" table in the database.
 *
 * @author Ensar Horozović
 */
@Entity()
@Table(schema = "core", name = "users")
@Getter
@Setter
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private String city;
    private String phone;
    private Integer photoId;
    private Date registrationDate;
    private byte[] secret;
}

package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an item in the system.
 * This entity is mapped to the "users" table in the database.
 *
 * @author Ensar Horozović
 */
@Entity()
@Table(schema = "core", name = "items")
@Getter
@Setter
public class ItemRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String address;

    private Integer photoId;

    private Double startPrice;

    private Date startDate;

    private Date endDate;

    private Integer subcategoryId;

    private Integer userId;
}

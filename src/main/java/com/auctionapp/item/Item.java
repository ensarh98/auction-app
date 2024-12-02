package com.auctionapp.item;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Represents an Item in the system.
 *
 * @author Ensar Horozović
 */

@Getter
@Setter
public class Item {

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

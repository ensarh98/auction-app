package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Table(schema = "core", name = "attachments")
@Getter
@Setter
public class AttachmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    private byte[] data;

    private String type;

}

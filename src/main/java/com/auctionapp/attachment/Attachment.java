package com.auctionapp.attachment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {

    private Integer id;

    private String filename;

    private byte[] data;

    private String type;
}

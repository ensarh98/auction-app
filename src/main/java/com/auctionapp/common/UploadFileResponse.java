package com.auctionapp.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponse {
    private Integer fileId;
    private ResponseStatus status;

    public enum ResponseStatus {
        UPLOAD_SUCCESSFUL
    }

    public UploadFileResponse(Integer fileId, ResponseStatus status) {
        this.fileId = fileId;
        this.status = status;
    }
}

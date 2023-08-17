package in.tryeasy.recordkeeping.repository.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUploadResponse {
    public enum Status{
        UPLOAD_SUCCESSFUL,UPLOAD_FAILURE
    }

    private Status uploadStatus;
}

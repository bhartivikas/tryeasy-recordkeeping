package in.tryeasy.recordkeeping.repository.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductUploadRequest {

    @CsvBindByName(column = "Id")
    private String id;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Active")
    private Boolean active;

    @CsvBindByName(column = "Description")
    private String description;
}

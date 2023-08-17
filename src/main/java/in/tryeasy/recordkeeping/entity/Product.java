package in.tryeasy.recordkeeping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private Boolean active;
    private String description;

}

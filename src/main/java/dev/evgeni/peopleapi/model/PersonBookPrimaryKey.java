package dev.evgeni.peopleapi.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class PersonBookPrimaryKey implements Serializable {

    private Long personId;
    private Long bookId;

}

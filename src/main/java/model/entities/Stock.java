package model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Entity;

@Data
@AllArgsConstructor
public class Stock implements Entity {

    private final String title;
    private final String url;
    private final Float buyCourse;
    private final Integer itemsNumber;
}
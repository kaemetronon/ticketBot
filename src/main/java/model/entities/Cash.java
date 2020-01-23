package model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Entity;

@Data
@AllArgsConstructor
public class Cash implements Entity {

    private final String title;
    private final Float amount;

    @Override
    public Integer getItemsNumber() {
        return 0;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public Float getBuyCourse() { return null;}
}

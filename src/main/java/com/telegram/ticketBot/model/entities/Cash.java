package com.telegram.ticketBot.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.telegram.ticketBot.model.Entity;

@Data
@AllArgsConstructor
public class Cash implements Entity {

    private String title;
    private Float amount;

    @Override
    public Integer getItemsNumber() {
        return 0;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public Float getBuyCourse() { return amount;}
}

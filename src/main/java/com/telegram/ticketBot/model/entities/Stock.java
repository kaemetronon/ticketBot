package com.telegram.ticketBot.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.telegram.ticketBot.model.Entity;

@Data
@AllArgsConstructor
public class Stock implements Entity {

    private String title;
    private String url;
    private Float buyCourse;
    private Integer itemsNumber;
}
package com.telegram.ticketBot.service;

import com.telegram.ticketBot.model.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class ViewMaker {

    private Map<String, Float[]> percentMap;
    private Float totalProfit;
    private Float cashAmount;
    StringBuilder tempBuilder = new StringBuilder();
    StringBuilder resultBuilder = new StringBuilder();

    public ViewMaker() {
    }

    public ViewMaker(Map<String, Float[]> percentMap, Float totalProfit) {
        this.percentMap = percentMap;
        this.totalProfit = totalProfit;
    }

    public ViewMaker(Map<String, Float[]> percentMap, Float totalProfit, Float cashAmount) {
        this.percentMap = percentMap;
        this.totalProfit = totalProfit;
        this.cashAmount = cashAmount;
    }

    private Float round(Float num) {
        return new BigDecimal(num)
                .setScale(2, RoundingMode.HALF_EVEN)
                .floatValue();
    }

    public StringBuilder format1Entity(String title, Float[] profitAndPercent) {

        tempBuilder.append(title)
                .append(": ")
                .append(round(profitAndPercent[0]))
                .append("₽ ")
                .append(round(profitAndPercent[1]))
                .append("%")
                .append("\n");
        return tempBuilder;
    }

    public String format4List(ArrayList<Entity> entityList) {
        for (Entity entity : entityList) {
            tempBuilder.append(entity.getTitle().toUpperCase())
                    .append(", ");
        }
        tempBuilder.setLength(tempBuilder.length() - 2);
        tempBuilder.append(".\n");
        return tempBuilder.toString();
    }

    public String format4Cash(Float amount) {
        tempBuilder.append("Free cash - ")
                .append(round(amount));
        return tempBuilder.toString();
    }

    public String format() {

        for (Map.Entry<String, Float[]> titlesProfitsPercents : percentMap.entrySet()) {
            format1Entity(titlesProfitsPercents.getKey()
                    , titlesProfitsPercents.getValue());

            resultBuilder.append(tempBuilder);
            tempBuilder.setLength(0);
        }

        resultBuilder.append("Total profit: ")
                .append(round(totalProfit))
                .append("₽ ")
                .append("\n");

        if (cashAmount != null) {
            resultBuilder.append(format4Cash(cashAmount));
        }

        return resultBuilder.toString();
    }
}

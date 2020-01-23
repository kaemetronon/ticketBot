package service;

import model.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class ViewMaker {

    private Map<String, Float[]> percentMap;
    private Float totalProfit;
    private Float cashAmount;
    StringBuilder builder;

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
        builder = new StringBuilder();
        builder.append(title)
                .append(" - ")
                .append(round(profitAndPercent[0]))
                .append("₽ ")
                .append(round(profitAndPercent[1]))
                .append("%")
                .append("\n");
        return builder;
    }

    public String format4List(ArrayList<Entity> entityList) {
        builder = new StringBuilder();
        for (Entity entity : entityList) {
            builder.append(entity.getTitle().toUpperCase())
                    .append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(".\n");
        return builder.toString();
    }

    public String format4Cash(Float amount) {
        builder = new StringBuilder();
        builder.append("Free cash - ")
                .append(round(amount));
        return builder.toString();
    }

    public String format() {
        builder = new StringBuilder();

        for (Map.Entry<String, Float[]> titlesProfitsPercents : percentMap.entrySet()) {
            Float[] profitAndPercent = titlesProfitsPercents.getValue();
            builder.append(format1Entity(titlesProfitsPercents.getKey()
                    , titlesProfitsPercents.getValue()));
        }

        builder.append("Total profit: ")
                .append(totalProfit)
                .append("₽ ")
                .append("\n");

        if (cashAmount != 0) {
            builder.append(format4Cash(cashAmount));
        }

        return builder.toString();
    }
}

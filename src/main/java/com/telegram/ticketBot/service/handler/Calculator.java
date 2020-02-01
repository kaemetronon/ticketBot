package com.telegram.ticketBot.service.handler;

public class Calculator {

    private Float buyCourse;
    private Float tempCourse;
    private Integer stocksNumber;
    private Float profit;
    private Float percent;

    public Calculator(Float buyCourse, Float tempCourse, Integer stocksNumber) {
        this.buyCourse = buyCourse;
        this.tempCourse = tempCourse;
        this.stocksNumber = stocksNumber;
    }

    private void calculatePercent() {
        percent = tempCourse / buyCourse * 100 - 100;
        profit = percent * buyCourse * stocksNumber / 100;
    }

    public Float[] getPercentAndProfit() {
        calculatePercent();
        return new Float[] {profit, percent};
    }
}

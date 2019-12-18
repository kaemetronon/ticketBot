package model;

import service.Request;

public class Ticket {

    private final String title;
    private final String url;
    private final Float buyCourse;
    private Float tempCourse;
    private final Integer stocksNumber;

    public Ticket(String title, String url, Float buyCourse, Integer stocksNumber) {
        this.title = title;
        this.url = url;
        this.buyCourse = (float) Math.round(buyCourse * 100)/100;
        this.stocksNumber = stocksNumber;
    }

    public Float setRequestAndGetTempCourse(Request request) {
        tempCourse = request.setUrlAndGetCourse(url);
        return tempCourse;
    }

    public Float getBuyCourse() {
        return buyCourse;
    }

    public Float getTempCourse() {
        return tempCourse;
    }

    public Integer getStocksNumber() {
        return stocksNumber;
    }

    public String getTitle() {
        return title;
    }
}
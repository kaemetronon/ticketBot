package service;


public class Calculator {

    private Float buyCourse;
    private Float tempCourse;
    private Integer stocksNumber;
    private Float amount;
    private Float percent;

    private void calculatePercent() {

        percent = 100 + buyCourse / tempCourse * (-100);
        percent = Float.valueOf(Math.round(percent * 10000));
        percent /= 10000;
        amount = percent * buyCourse * stocksNumber / 100;
    }

    public Float[] getPercentAndAmount() {
        calculatePercent();
        return new Float[]{percent, amount};
    }

    public void setBuyCourse(Float buyCourse) {
        this.buyCourse = buyCourse;
    }

    public void setTempCourse(Float tempCourse) {
        this.tempCourse = tempCourse;
    }

    public void setStocksNumber(Integer stocksNumber) {
        this.stocksNumber = stocksNumber;
    }
}

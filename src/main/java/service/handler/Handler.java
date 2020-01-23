package service.handler;

import exceptions.UncorrectInputDataException;
import model.Command;
import model.Entity;
import model.SingletonList;
import model.entities.Cash;
import model.entities.ETF;
import model.entities.Stock;
import service.RequestMaker;
import service.ViewMaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Handler {

    private Command command;
    private Entity entity;
    boolean exceptionExists;

    private ArrayList<Entity> entityList;

    public Handler(String text) {
        try {
            CommandDeterminator.determinate(text);
        } catch (UncorrectInputDataException e) {
            e.printStackTrace();
            System.err.println("Input: " + text);
            exceptionExists = true;
        }
        command = CommandDeterminator.getCommand();
        entity = CommandDeterminator.getEntity();
        entityList = SingletonList.getArrayList();
    }

    public String action() {
        if (exceptionExists) return "Uncorrect data.";
        if (entity == null)
            return notCertainTitket();
        else
            return certainTitket();
    }

    private String notCertainTitket() {

        RequestMaker request;
        Calculator calculator;
        ViewMaker view;

        Float[] result;
        Float profitCounter = 0F;
        Map<String, Float[]> resultMap = new HashMap<>();

        switch (command) {
            case STOCKS: {
                ArrayList<Stock> stockList = new ArrayList<>();
                for (Entity entity1 : entityList) {
                    if (entity1.getClass().getSimpleName().toUpperCase().equals("STOCK")) {
                        stockList.add((Stock) entity1);
                    }
                }
                for (Stock stock : stockList) {
                    request = new RequestMaker(stock.getUrl());
                    calculator = new Calculator(stock.getBuyCourse(), request.getCourse(), stock.getItemsNumber());
                    result = calculator.getPercentAndProfit();
                    profitCounter += result[0];
                    resultMap.put(stock.getTitle(), result);
                    System.out.println(stock.getTitle() + " completed");
                }
                view = new ViewMaker(resultMap, profitCounter);
                System.out.println("Done");
                return view.format();
            }
            case ETFS: {
                ArrayList<ETF> etfList = new ArrayList<>();
                for (Entity entity1 : entityList) {
                    if (entity1.getClass().getSimpleName().toUpperCase().equals("ETF")) {
                        etfList.add((ETF) entity1);
                    }
                }

                for (ETF etf : etfList) {
                    request = new RequestMaker(etf.getUrl());
                    calculator = new Calculator(etf.getBuyCourse(), request.getCourse(), etf.getItemsNumber());
                    result = calculator.getPercentAndProfit();
                    profitCounter += result[0];
                    resultMap.put(etf.getTitle(), result);
                    System.out.println(etf.getTitle() + " completed");
                }
                view = new ViewMaker(resultMap, profitCounter);
                System.out.println("Done");
                return view.format();
            }
            case BONDS: {
                // TODO: 22.01.2020
                return "will be added in future releases";
            }
            case CASH: {
                Cash cash = null;
                for (Entity isCash : entityList) {
                    if (isCash.getTitle().toUpperCase().equals("CASH")) {
                        cash = (Cash) isCash;
                    }
                }
                view = new ViewMaker();
                return view.format4Cash(cash.getAmount());
            }
            case ALL: {
                Float cashExists = 0F;
                for (Entity entity : entityList) {
                    if (entity.getUrl() == null) {
                        cashExists = entity.getBuyCourse();
                        continue;
                    }
                    request = new RequestMaker(entity.getUrl());
                    calculator = new Calculator(entity.getBuyCourse(), request.getCourse(), entity.getItemsNumber());
                    result = calculator.getPercentAndProfit();
                    profitCounter += result[0];
                    resultMap.put(entity.getTitle(), result);
                    System.out.println(entity.getTitle() + " completed");
                }
                if (cashExists != 0) {
                    view = new ViewMaker(resultMap, profitCounter, cashExists);
                } else {
                    view = new ViewMaker(resultMap, profitCounter);
                }
                System.out.println("Done");
                return view.format();
            }
            case LIST: {
                view = new ViewMaker();
                return view.format4List(entityList);
            }
        }
        return null;
    }

    private String certainTitket() {

        ViewMaker view;
        RequestMaker request;
        Calculator calculator;
        Float[] result;

        request = new RequestMaker(entity.getUrl());
        calculator = new Calculator(entity.getBuyCourse(), request.getCourse(), entity.getItemsNumber());
        result = calculator.getPercentAndProfit();

        view = new ViewMaker();
        return view.format1Entity(entity.getTitle(), result).toString();
    }
}
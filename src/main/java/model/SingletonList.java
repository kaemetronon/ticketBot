package model;

import model.entities.Cash;
import model.entities.ETF;
import model.entities.Stock;

import java.util.ArrayList;

public class SingletonList {

    private static ArrayList<Entity> ticketList;

    public static ArrayList<Entity> getArrayList() {
        if (ticketList == null) {
            ticketList = new ArrayList<Entity>() {
                {
                    String moexTitle = "MOEX";
                    String rstiTitle = "RSTI";
                    String fxgdTitle = "FXGD";
                    String dskyTitle = "DSKY";
                    String cashTitle = "CASH";

                    String moexUrl = "https://www.investing.com/equities/moskovskaya-birzha-oao";
                    String rstiUrl = "https://www.investing.com/equities/rosseti-ao";
                    String fxgdUrl = "https://www.investing.com/etfs/finex-physically-held-gold-usd";
                    String dskyUrl = "https://www.investing.com/equities/detskiy-mir-pao";

                    Float moexBuyCourse = 95.53F;
                    Float rstiBuyCourse = 1.2436F;
                    Float fxgdBuyCourse = 662.1F;
                    Float dskyBuyCourse = 92.88F;
                    Float cashAmount = 6642.82F;

                    Integer moexStocksNumber = 20;
                    Integer rstiStocksNumber = 1000;
                    Integer fxgdStocksNumber = 2;
                    Integer dskyStockNumber = 20;

                    add(new Stock(moexTitle, moexUrl, moexBuyCourse, moexStocksNumber));
                    add(new Stock(rstiTitle, rstiUrl, rstiBuyCourse, rstiStocksNumber));
                    add(new Stock(dskyTitle, dskyUrl, dskyBuyCourse, dskyStockNumber));
                    add(new ETF(fxgdTitle, fxgdUrl, fxgdBuyCourse, fxgdStocksNumber));
                    add(new Cash(cashTitle, cashAmount));
                }
            };
        }
            return ticketList;
    }
}

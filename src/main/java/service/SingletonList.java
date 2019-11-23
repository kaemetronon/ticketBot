package service;

import model.Ticket;

import java.util.ArrayList;

public class SingletonList {

    private static ArrayList<Ticket> ticketList;

    public static ArrayList<Ticket> getArrayList() {
        if (ticketList == null) {
            ticketList = new ArrayList<Ticket>() {
                {
                    String moexTitle = "MOEX";
                    String rstiTitle = "RSTI";
                    String fxgdTitle = "FXGD";
                    String dskyTitle = "DSKY";

                    String moexUrl = "https://www.investing.com/equities/moskovskaya-birzha-oao";
                    String rstiUrl = "https://www.investing.com/equities/rosseti-ao";
                    String fxgdUrl = "https://www.investing.com/etfs/finex-physically-held-gold-usd";
                    String dskyUrl = "https://www.investing.com/equities/detskiy-mir-pao";

                    Float moexBuyCourse = 95.53F;
                    Float rstiBuyCourse = 1.2436F;
                    Float fxgdBuyCourse = 662.1F;
                    Float dskyBuyCourse = 92.88F;

                    Integer moexStocksNumber = 20;
                    Integer rstiStocksNumber = 1000;
                    Integer fxgdStocksNumber = 2;
                    Integer dskyStockNumber = 20;

                    add(new Ticket(moexTitle, moexUrl, moexBuyCourse, moexStocksNumber));
                    add(new Ticket(rstiTitle, rstiUrl, rstiBuyCourse, rstiStocksNumber));
                    add(new Ticket(fxgdTitle, fxgdUrl, fxgdBuyCourse, fxgdStocksNumber));
                    add(new Ticket(dskyTitle, dskyUrl, dskyBuyCourse, dskyStockNumber));
                }
            };
        }
            return ticketList;
    }
}

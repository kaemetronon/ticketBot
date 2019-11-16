package service;

import model.Command;
import model.Ticket;

import java.util.ArrayList;

public class Handler {

    static ArrayList<Ticket> ticketList;

    static {
        ticketList = new ArrayList<Ticket>() {
            {
                String moexTitle = "MOEX";
                String rstiTitle = "RSTI";
                String fxgdTitle = "FXGD";

                String moexUrl = "https://www.investing.com/equities/moskovskaya-birzha-oao";
                String rstiUrl = "https://www.investing.com/equities/rosseti-ao";
                String fxgdUrl = "https://www.investing.com/etfs/finex-physically-held-gold-usd";

                Float moexBuyCourse = 95.53F;
                Float rstiBuyCourse = 1.2436F;
                Float fxgdBuyCourse = 662.1F;

                Integer moexStocksNumber = 20;
                Integer rstiStocksNumber = 1000;
                Integer fxgdStocksNumber = 2;

                add(new Ticket(moexTitle, moexUrl, moexBuyCourse, moexStocksNumber));
                add(new Ticket(rstiTitle, rstiUrl, rstiBuyCourse, rstiStocksNumber));
                add(new Ticket(fxgdTitle, fxgdUrl, fxgdBuyCourse, fxgdStocksNumber));
            }
        };
    }

    private Request request;
    private Calculator calculator;
    private Ticket ticket;
    private View view;
    private Float tempCourse;
    private Command command;

    private boolean isCorrect;
    private Float[] percentAndAmount;
    private String message;

    public Handler(String text) {
        handleText(text);
    }

    private void handleText(String text) {

        if (text.toUpperCase().equals("ALL")) {
            command = Command.ALL;
            isCorrect = true;
        }
        if (text.toUpperCase().equals("LIST")) {
            command = Command.LIST;
            isCorrect = true;
        }
        for (Ticket ticket : ticketList) {
            if (ticket.getTitle().equals(text.toUpperCase())) {
                this.ticket = ticket;
                command = Command.TICKET;
                isCorrect = true;
            }
        }
//        окей, здесь какая то фигня, вернусь, как осознаю лямбды
//        ticketList.forEach(title -> title.equals(text.toUpperCase()) ? isCorrect = true : isCorrect = false);
        if (isCorrect == false) {
            message = "Uncorrect request";
        }
    }

    public String action() {

        view = new View();
        switch (command) {
            case TICKET: {
                request = new Request();
                tempCourse = ticket.setRequestAndGetTempCourse(request);

                calculator = new Calculator();
                calculator.setBuyCourse(ticket.getBuyCourse());
                calculator.setTempCourse(tempCourse);
                calculator.setStocksNumber(ticket.getStocksNumber());

                percentAndAmount = calculator.getPercentAndAmount();
                return view.setFloatsAndFormat(percentAndAmount);
            }
            case ALL: {
                request = new Request();
                Float counter = 0F;
                for (Ticket ticket : ticketList) {
                    if (!ticket.getTitle().equals("ALL")) {

                        tempCourse = ticket.setRequestAndGetTempCourse(request);

                        calculator = new Calculator();
                        calculator.setBuyCourse(ticket.getBuyCourse());
                        calculator.setTempCourse(tempCourse);
                        calculator.setStocksNumber(ticket.getStocksNumber());

                        counter += calculator.getPercentAndAmount()[1];
                    }
                }
                counter = Float.valueOf(Math.round(counter * 10000));
                counter /= 10000;
                return view.setFloatsAndFormat(counter);
            }
            case LIST: {
                return view.setListAndGetView(ticketList);
            }
            default: return null;
        }
    }

    public boolean getCorrect() {
        return isCorrect;
    }

    public String getMessage() {
        return message;
    }
}
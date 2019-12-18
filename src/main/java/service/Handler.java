package service;

import model.Command;
import model.Ticket;

import java.util.ArrayList;

public class Handler {

    private Request request;
    private Calculator calculator;
    private Ticket ticket;
    private View view;
    private Float tempCourse;
    private Command command;
    private SingletonList singletonList;

    private boolean isCorrect;
    private Float[] percentAndAmount;
    private String message;
    private ArrayList<Ticket> ticketList;

    public Handler(String text) {
        ticketList = SingletonList.getArrayList();
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
                StringBuilder sb = new StringBuilder();
                Float[] concretePercentAndAmount = new Float[2];
                for (Ticket ticket : ticketList) {
                    if (!ticket.getTitle().equals("ALL")) {

                        tempCourse = ticket.setRequestAndGetTempCourse(request);

                        calculator = new Calculator();
                        calculator.setBuyCourse(ticket.getBuyCourse());
                        calculator.setTempCourse(tempCourse);
                        calculator.setStocksNumber(ticket.getStocksNumber());

                        concretePercentAndAmount = calculator.getPercentAndAmount();

                        counter += concretePercentAndAmount[1];

                        sb.append(ticket.getTitle() + ": " + concretePercentAndAmount[1] + " "
                                + concretePercentAndAmount[0] + "%" + "\n");
                    }
                }
                counter = Float.valueOf(Math.round(counter * 10000));
                counter /= 10000;
                sb.append("Amount: " + counter);
                return view.setFloatsAndFormat(sb);
            }
            case LIST: {
                return view.setListAndGetView(ticketList);
            }
            default:
                return null;
        }
    }

    public boolean getCorrect() {
        return isCorrect;
    }

    public String getMessage() {
        return message;
    }
}
package service;

import model.Ticket;

import java.util.ArrayList;
import java.util.Iterator;

public class View {

    private Float percent;
    private Float amount;
    private StringBuilder sb;

    //for one
    public String setFloatsAndFormat(Float[] percentAndAmount) {
        this.percent = percentAndAmount[0];
        this.amount = percentAndAmount[1];
        return format();
    }

    //for all
    public String setFloatsAndFormat(StringBuilder sb) {
        this.amount = amount;
        this.sb = sb;
        return format();
    }

    public String setListAndGetView(ArrayList<Ticket> ticketList) {
        StringBuilder sb = new StringBuilder();
        Iterator<Ticket> iterator = ticketList.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getTitle() + "\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    public String format() {
            StringBuilder builder = new StringBuilder();
            if (percent != null) {
                builder.append("Percent: " + percent.toString());
                builder.append("\n");
                builder.append("Amount: " + amount.toString());
            }
            else {
                builder.append(sb);
            }
            return builder.toString();

    }

}

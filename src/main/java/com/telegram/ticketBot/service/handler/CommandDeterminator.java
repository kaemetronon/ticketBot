package com.telegram.ticketBot.service.handler;

import com.telegram.ticketBot.exceptions.UncorrectInputDataException;
import com.telegram.ticketBot.model.Command;
import com.telegram.ticketBot.model.Entity;
import com.telegram.ticketBot.model.SingletonList;

import java.util.ArrayList;

public class CommandDeterminator {

    private static Command command;
    private static Entity entity;

    public static void determinate(String text) throws UncorrectInputDataException {
//        STOCKS, ETFS, BONDS, CASH, ALL, LIST,
//        ONE_ITEM
        command = null;
        entity = null;
        switch (text.toUpperCase()) {
            case "STOCKS": {
                command = Command.STOCKS;
                break;
            }
            case "ETFS": {
                command = Command.ETFS;
                break;
            }
            case "BONDS": {
                command = Command.BONDS;
                break;
            }
            case "CASH": {
                command = Command.CASH;
                break;
            }
            case "ALL": {
                command = Command.ALL;
                break;
            }
            case "LIST": {
                command = Command.LIST;
                break;
            }
            default: {
                ArrayList<Entity> entityList = SingletonList.getArrayList();
                for (Entity concreteEntity : entityList) {
                    if (concreteEntity.getTitle().equals(text.toUpperCase())) {
                        entity = concreteEntity;
                        command = Command.ONE_ITEM;
                    }
                }
            }
        }
        if (command == null) throw new UncorrectInputDataException();
    }

    public static Command getCommand() {
        return command;
    }

    public static Entity getEntity() {
        if (entity != null)
            return entity;
        else return null;
    }
}

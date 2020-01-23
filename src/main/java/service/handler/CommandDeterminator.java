package service.handler;

import exceptions.UncorrectInputDataException;
import model.Command;
import model.Entity;
import model.SingletonList;

import java.util.ArrayList;

public class CommandDeterminator {

    private static Command command;
    private static Entity entity;

    public static void determinate(String text) throws UncorrectInputDataException {
//        STOCKS, ETFS, BONDS, CASH, ALL, LIST,
//        ONE_STOCK, ONE_ETF, ONE_BOND
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
                    if (entity.getTitle().equals(text.toUpperCase())) {
                        entity = concreteEntity;
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

package model;

import model.entities.Cash;
import model.entities.ETF;
import model.entities.Stock;
import org.checkerframework.checker.units.qual.A;
import org.xml.sax.SAXException;
import service.xmlParsing.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class SingletonList {

    private static ArrayList<Entity> ticketList;

    public static ArrayList<Entity> getArrayList() {
        if (ticketList == null) {

            ticketList = new ArrayList<Entity>();

            XmlParser parser = null;
            try {
                parser = new XmlParser();
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }

            ArrayList<Entity> entities = new ArrayList<>();

            entities.addAll(parser.getStocks());
            if (!entities.isEmpty()) {
                ticketList.addAll(entities);
                entities.clear();
            }
            entities.addAll(parser.getEtfs());
            if (!entities.isEmpty()) {
                ticketList.addAll(entities);
                entities.clear();
            }
//            entities.addAll(parser.getBonds());
//            if (!entities.isEmpty()) {
//                ticketList.addAll(entities);
//                entities.clear();
//            }
            entities.add(parser.getCash());
            if (!entities.isEmpty()) {
                ticketList.addAll(entities);
                entities.clear();
            }
        }
        return ticketList;
    }
}

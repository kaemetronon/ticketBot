package com.telegram.ticketBot.service.xmlParsing;

import com.telegram.ticketBot.model.entities.Bond;
import com.telegram.ticketBot.model.entities.Cash;
import com.telegram.ticketBot.model.entities.ETF;
import com.telegram.ticketBot.model.entities.Stock;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlParser {

    Document document;

    public XmlParser() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File("src/main/resources/stockList.xml"));
        document.getDocumentElement().normalize();
    }

    public ArrayList<Stock> getStocks() {

        NodeList stock;
        ArrayList<Stock> stocks = new ArrayList<>();

        stock = document.getElementsByTagName("stock");
        for (int i = 0; i < stock.getLength(); i++) {
            Node node = stock.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                stocks.add(new Stock(element.getAttribute("title"),
                        element.getAttribute("url"),
                        Float.valueOf(element.getAttribute("buyCourse")),
                        Integer.valueOf(element.getAttribute("itemNumber"))));
            }
        }
        return stocks;
    }

    public ArrayList<ETF> getEtfs() {

        NodeList etf;
        ArrayList<ETF> etfs = new ArrayList<>();

        etf = document.getElementsByTagName("etf");
        for (int i = 0; i < etf.getLength(); i++) {
            Node node = etf.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                etfs.add(new ETF(element.getAttribute("title"),
                        element.getAttribute("url"),
                        Float.valueOf(element.getAttribute("buyCourse")),
                        Integer.valueOf(element.getAttribute("itemNumber"))));
            }
        }
        return etfs;
    }

    public ArrayList<Bond> getBonds() {

        return new ArrayList<>();
    }

    public Cash getCash() {

        NodeList cash = document.getElementsByTagName("cash");
        Node node = cash.item(0);
        Element element = (Element) node;
        return new Cash("Cash", Float.valueOf(element.getAttribute("amount")));
    }
}

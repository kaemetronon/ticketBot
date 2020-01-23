package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RequestMaker {

    private String url;

    public RequestMaker(String url) {
        this.url = url;
    }

    public Float getCourse() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div#quotes_summary_current_data.instrumentDataFlex");
            return Float.valueOf(elements.text().split("\\s")[0]);
        } catch (IOException e) {return 0F;}
    }
}

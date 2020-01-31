package parser;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HtmlParser {
    private static String URL;

    public HtmlParser(String url) {
        URL = url;
    }

    public Metro getMetro(Elements elements) {
        Metro metro = new Metro();
        elements.forEach(element -> {
            String stationName = getStationName(element);
            Elements linesNames = element.select("td:nth-child(1) img");
            Elements linesNumbers = element.select("td:nth-child(1) span.sortkey:not(:last-child)");
            for (int e = 0; e < linesNames.size(); e++) {
                String lineName = linesNames.get(e).attr("alt");
                String lineNumber = linesNumbers.get(e).text().replaceFirst("^0", "");
                if (linesNames.size() == linesNumbers.size() && !stationName.equals("") && !lineName.equals("")) {
                    Line line = new Line(lineName, lineNumber);
                    if (metro.getLine(line) == null) {
                        metro.addLine(line);
                    } else {
                        line = metro.getLine(line);
                    }
                    Station station = new Station(stationName, line.getNumber());
                    metro.addStation(station);
                }
            }
        });
        return metro;
    }

    public Elements htmlFromURL() {
        try {
            Document document = Jsoup.connect(URL).maxBodySize(0).get();
            return document.select(".standard.sortable tbody tr");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        public String getStationName(Element element) {
        Elements stationNames = element.select("td:nth-child(2):not(:matches(.+закрыта.+))");
            String stationName = stationNames.select("td > span > a:first-child").text();
            if (stationName.equals("")) {
                stationName = stationNames.select("td > a:first-child").text();
            }
            return stationName;
        }
}
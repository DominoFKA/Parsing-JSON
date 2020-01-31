package parser;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class Metro {

    TreeMap<String, ArrayList<String>> stations;
    List<Line> lines;

    public Metro() {
        LineComparator lineComparator = new LineComparator();
        stations = new TreeMap<>(lineComparator);
        lines = new ArrayList<>();
    }

    public void addStation(Station station) {
        if (station != null) {
            stations.get(station.getLine()).add(station.getStationName());
        }
    }

    public void addLine(Line line) {
        if (line != null) {
            lines.add(line);
            stations.put(line.getNumber(), new ArrayList<>());
        }
    }

    public Line getLine(Line line) {
        for (Line currentLine : lines) {
            if (currentLine.equals(line))
                return currentLine;
        }
        return null;
    }

    public TreeMap<String, ArrayList<String>> getStations() {
        return stations;
    }

    public static void getNumberStationsOnOneLine(Metro metro) {
        System.out.println("Number of stations on one line:");
        metro.getStations().forEach((lineNumber, listStations) ->
                System.out.printf("On line № %2s there are %2d stations %n", lineNumber, listStations.size()));
    }
}
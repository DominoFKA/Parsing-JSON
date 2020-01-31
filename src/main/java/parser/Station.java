package parser;

import java.util.Objects;

public class Station {

    private String lineNumber;
    private String stationName;

    public Station(String name, String line)
    {
        this.stationName = name;
        this.lineNumber = line;
    }

    public String  getLine()
    {
        return lineNumber;
    }

    public String getStationName()
    {
        return stationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return Objects.equals(lineNumber, station.lineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber);
    }
}
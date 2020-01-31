package parser;

import java.util.Objects;

public class Line {
    private String lineNumber;
    private String lineName;

    public Line(String name, String  number)
    {
        this.lineName = name;
        this.lineNumber = number;
    }

    public String getName() {
        return lineName;
    }

    public String getNumber() {
        return lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(lineNumber, line.lineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber);
    }
}
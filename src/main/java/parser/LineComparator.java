package parser;

import java.util.Comparator;

public class LineComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        double s1, s2;
        if (o1.matches("\\d+\\D")) {
            char ch = o1.charAt(o1.length() - 1);
            s1 = Double.parseDouble(o1.replaceAll("[^0-9]", "")) + Double.parseDouble(Integer.toHexString(ch)) / 1000;
        }
        else {
            s1 = Double.parseDouble(o1);
        }

        if (o2.matches("\\d+\\D")) {
            char ch = o2.charAt(o2.length() - 1);
            s2 = Double.parseDouble(o2.replaceAll("[^0-9]", "")) + Double.parseDouble(Integer.toHexString(ch)) / 1000;
        }
        else {
            s2 = Double.parseDouble(o2);
        }

        return Double.compare(s1, s2);
    }
}
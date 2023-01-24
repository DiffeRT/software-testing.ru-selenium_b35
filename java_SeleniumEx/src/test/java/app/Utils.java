package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    // Get RGB from the string
    public static HashMap<String, Integer> getColor(String color) {
        StringBuilder sb = new StringBuilder();
        List<String> cl = new ArrayList<>();
        int pos = 0;
        while (pos < color.length()) {
            char c = color.charAt(pos);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
            else if ((c == ',' || c == ')') && sb.length() > 0) {
                cl.add(sb.toString());
                sb.setLength(0);
            }
            pos++;
        }
        HashMap<String, Integer> result = new HashMap<>();
        result.put( "R", Integer.parseInt(cl.get(0)) );
        result.put( "G", Integer.parseInt(cl.get(1)) );
        result.put( "B", Integer.parseInt(cl.get(2)) );
        return result;
    }

    //Get height from the string
    public static  float getHeight(String height) {
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        while (pos < height.length()) {
            char c = height.charAt(pos);
            if ((c >= '0' && c <= '9') || c == '.') {
                sb.append(c);
            }
            pos++;
        }
        return Float.parseFloat(sb.toString());
    }
}

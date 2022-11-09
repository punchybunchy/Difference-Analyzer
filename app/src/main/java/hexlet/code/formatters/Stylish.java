package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public class Stylish {
    public static String getStylishFormat(List<Map<String, Object>> resultList) {

        StringBuilder str = new StringBuilder();
        String result;

        for (Map<String, Object> element : resultList) {
            String key = element.get("key").toString();
            String status = element.get("status").toString();
            Object value = element.get("value");
            Object oldValue = element.get("oldValue");
            Object newValue = element.get("newValue");

            switch (status) {
                case ("UNCHANGED") -> str.append(getLine(" ", key, value));
                case ("ADDED") -> str.append(getLine("+", key, value));
                case ("DELETED") -> str.append(getLine("-", key, value));
                case ("CHANGED") -> {
                    str.append(getLine("-", key, oldValue));
                    str.append(getLine("+", key, newValue));
                }
                default -> throw new Error("Unknown status: " + status);
            }
        }

        str.insert(0, "{\n")
                .append("}");
        result = str.toString();
        return result;
    }

    private static String getLine(String status, String key, Object value) {
        return String.format("  %s %s: %s\n", status, key, value);
    }
}

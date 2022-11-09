package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String getPlainFormat(List<Map<String, Object>> resultList) {

        StringBuilder str = new StringBuilder();
        String result;

        for (Map<String, Object> element : resultList) {
            String key = element.get("key").toString();
            String status = element.get("status").toString();
            String value = getFormattedObject(element.get("value"));
            String oldValue = getFormattedObject(element.get("oldValue"));
            String newValue = getFormattedObject(element.get("newValue"));

            switch (status) {
                case ("UNCHANGED") -> { }
                case ("DELETED") -> str.append(getLine(key));
                case ("ADDED") -> str.append(getLine(key, value));
                case ("CHANGED") -> str.append(getLine(key, oldValue, newValue));
                default -> throw new Error("Unknown status: " + status);
            }
        }
        str.deleteCharAt(str.length() - 1);
        result = str.toString();
        return result;
    }

    private static String getFormattedObject(Object values) {
        if (values instanceof String) {
            values = "'" + values + "'";
        }
        if (values instanceof Map<?, ?> || values instanceof List<?>) {
            values =  "[complex value]";
        }
        return values == null ? null : values.toString();
    }

    private static String getLine(String key) {
        return String.format("Property '%s' was removed\n", key);
    }

    private static String getLine(String key, String value) {
        return String.format("Property '%s' was added with value: %s\n", key, value);
    }

    private static String getLine(String key, String oldValue, String newValue) {
        return String.format("Property '%s' was updated. From %s to %s\n", key, oldValue, newValue);
    }
}

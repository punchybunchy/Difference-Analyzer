package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String formatSelection(String formatName, Map<String, Object> resultMap) {
        String formatedResult = "";
        if (formatName.equals("stylish")) {
            formatedResult = Stylish.getStylishFormat(resultMap);
        } else if (formatName.equals("plain")) {
            formatedResult = Plain.getPlainFormat(resultMap);
        }
        return formatedResult;
    }
}

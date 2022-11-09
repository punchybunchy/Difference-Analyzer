package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatSelection(String formatName, List<Map<String, Object>> resultList) throws Exception {

        String formattedResult;

        switch (formatName) {
            case ("stylish") -> {
                formattedResult = Stylish.getStylishFormat(resultList);
            }
            case ("plain") -> {
                formattedResult = Plain.getPlainFormat(resultList);
            }
            case ("json") -> {
                formattedResult = Json.getJsonFormat(resultList);
            }
            default -> throw new Exception("Not valid format: " + formatName);
        }
        return formattedResult;
    }
}

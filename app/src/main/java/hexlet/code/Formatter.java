package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String formatSelection(String formatName, Map<String, Object> diffResultMap) throws IOException {

        String formatedResult = "";
        final String formatStylish = "stylish";
        final String formatPlain = "plain";
        final String formatJson = "json";

        switch (formatName) {
            case (formatStylish) -> {
                formatedResult = Stylish.getStylishFormat(diffResultMap);
            }
            case (formatPlain) -> {
                formatedResult = Plain.getPlainFormat(diffResultMap);
            }
            case (formatJson) -> {
                formatedResult = Json.getJsonFormat(diffResultMap);
            }
            default -> System.out.println("Please retry and input correct format");
        }
        return formatedResult;
    }
}

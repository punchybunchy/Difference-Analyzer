package hexlet.code.formatters;

import java.util.Map;


public class Stylish {
    public static String getStylishFormat(Map<String, Object> resultMap) {

        final String notChangedItemPref = "* ";
        final String changedOldItemPref = "- ";
        final String changedNewItemPref = "+ ";
        final String removedItemPref = "-- ";
        final String addedItemPref = "++ ";

        StringBuilder str = new StringBuilder();
        String result;

        for (String key : resultMap.keySet()) {
            if (key.startsWith(notChangedItemPref)) {
                str.append("    ")
                        .append(key.substring(notChangedItemPref.length()))
                        .append(": ").append(resultMap.get(key)).append("\n");
            } else if (key.startsWith(changedOldItemPref)) {
                str.append("  - ")
                        .append(key.substring(changedOldItemPref.length()))
                        .append(": ").append(resultMap.get(key)).append("\n");
            } else if (key.startsWith(changedNewItemPref)) {
                str.append("  + ")
                        .append(key.substring(changedNewItemPref.length()))
                        .append(": ").append(resultMap.get(key)).append("\n");
            } else if (key.startsWith(removedItemPref)) {
                str.append("  - ")
                        .append(key.substring(removedItemPref.length()))
                        .append(": ").append(resultMap.get(key)).append("\n");
            } else if (key.startsWith(addedItemPref)) {
                str.append("  + ")
                        .append(key.substring(addedItemPref.length()))
                        .append(": ").append(resultMap.get(key)).append("\n");
            }
        }
        str.insert(0, "{\n")
                .append("}");
        result = str.toString();
        return result;
    }
}

package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String getPlainFormat(Map<String, Object> resultMap) {

        final String changedOldItemPref = "- ";
        final String changedNewItemPref = "+ ";
        final String removedItemPref = "-- ";
        final String addedItemPref = "++ ";

        StringBuilder str = new StringBuilder();
        String result;

        for (String key : resultMap.keySet()) {
            if (key.startsWith(changedOldItemPref)) {
                str.append("Property '")
                        .append(key.substring(changedOldItemPref.length()))
                        .append("' was updated. From ");
                if (resultMap.get(key) instanceof String) {
                    str.append("'").append(resultMap.get(key)).append("'");
                } else {
                    str.append(resultMap.get(key));
                }

            } else if (key.startsWith(changedNewItemPref)) {
                str.append(" to ");
                if (resultMap.get(key) instanceof String) {
                    str.append("'").append(resultMap.get(key)).append("'\n");
                } else {
                    str.append(resultMap.get(key)).append("\n");
                }

            } else if (key.startsWith(removedItemPref)) {
                str.append("Property '")
                        .append(key.substring(removedItemPref.length())).append("' was removed\n");

            } else if (key.startsWith(addedItemPref)) {
                str.append("Property '")
                        .append(key.substring(addedItemPref.length())).append("' was added with value: ");
                if (resultMap.get(key) instanceof String) {
                    str.append("'").append(resultMap.get(key)).append("'\n");
                } else {
                    str.append(resultMap.get(key)).append("\n");
                }
            }
        }
        result = str.toString().replaceAll("\\{.*?}", "[complex value]")
                .replaceAll("\\[.*?]", "[complex value]");
        return result;
    }
}

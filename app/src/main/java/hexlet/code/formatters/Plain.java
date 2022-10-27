package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String getPlainFormat(Map<String, Object> diffResultMap) {

        final String changedOldItemPref = "- ";
        final String changedNewItemPref = "+ ";
        final String removedItemPref = "-- ";
        final String addedItemPref = "++ ";

        StringBuilder str = new StringBuilder();
        String result;

        for (String key : diffResultMap.keySet()) {
            if (key.startsWith(changedOldItemPref)) {
                str.append("Property '")
                        .append(key.substring(changedOldItemPref.length()))
                        .append("' was updated. From ");
                if (diffResultMap.get(key) instanceof String) {
                    str.append("'").append(diffResultMap.get(key)).append("'");
                } else {
                    str.append(diffResultMap.get(key));
                }

            } else if (key.startsWith(changedNewItemPref)) {
                str.append(" to ");
                if (diffResultMap.get(key) instanceof String) {
                    str.append("'").append(diffResultMap.get(key)).append("'\n");
                } else {
                    str.append(diffResultMap.get(key)).append("\n");
                }

            } else if (key.startsWith(removedItemPref)) {
                str.append("Property '")
                        .append(key.substring(removedItemPref.length())).append("' was removed\n");

            } else if (key.startsWith(addedItemPref)) {
                str.append("Property '")
                        .append(key.substring(addedItemPref.length())).append("' was added with value: ");
                if (diffResultMap.get(key) instanceof String) {
                    str.append("'").append(diffResultMap.get(key)).append("'\n");
                } else {
                    str.append(diffResultMap.get(key)).append("\n");
                }
            }
        }
        result = str.toString().replaceAll("\\{.*?}", "[complex value]")
                .replaceAll("\\[.*?]", "[complex value]");
        return result;
    }
}

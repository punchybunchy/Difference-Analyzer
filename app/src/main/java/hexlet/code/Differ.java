package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        final String notChangedItemPref = "* ";
        final String changedOldItemPref = "- ";
        final String changedNewItemPref = "+ ";
        final String removedItemPref = "-- ";
        final String addedItemPref = "++ ";

        Map<String, Object> unitedMap = new TreeMap<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();

        Map<String, Object> map1 = Parser.parserFilesToMap(filePath1);
        Map<String, Object> map2 = Parser.parserFilesToMap(filePath2);

        unitedMap.putAll(map1);
        unitedMap.putAll(map2);

        for (String key : unitedMap.keySet()) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    resultMap.put(notChangedItemPref + key, map1.get(key));
                } else {
                    resultMap.put(changedOldItemPref + key, map1.get(key));
                    resultMap.put(changedNewItemPref + key, map2.get(key));
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                resultMap.put(removedItemPref + key, map1.get(key));
            } else {
                resultMap.put(addedItemPref + key, map2.get(key));
            }
        }

        String result = Formatter.formatSelection(formatName, resultMap);
        return result;
    }
}

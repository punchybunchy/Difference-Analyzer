package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> unitedMap = new TreeMap<>();
        List<String> resultList = new ArrayList<>();
        String result;

        if (map1.isEmpty() || map2.isEmpty()) {
            result = "One of the files or both files are empty";
            return result;
        }

        unitedMap.putAll(map1);
        unitedMap.putAll(map2);

        for (String key : unitedMap.keySet()) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    resultList.add("  " + key + ": " + map1.get(key));
                } else {
                    resultList.add("- " + key + ": " + map1.get(key));
                    resultList.add("+ " + key + ": " + map2.get(key));
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                resultList.add("- " + key + ": " + map1.get(key));
            } else {
                resultList.add("+ " + key + ": " + map2.get(key));
            }
        }

        result = resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n", "{\n", "\n}"));

        return result;
    }
}

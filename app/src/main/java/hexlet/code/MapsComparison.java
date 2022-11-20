package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class MapsComparison {

    public static List<Map<String, Object>> getMapsComparing(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>();
        List<Map<String, Object>> resultList = new ArrayList<>();

        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key: keys) {
            if (!map1.containsKey(key)) {
                resultList.add(getMapChange(key, "ADDED", map2.get(key)));
            } else if (!map2.containsKey(key)) {
                resultList.add(getMapChange(key, "DELETED", map1.get(key)));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                resultList.add(getMapChange(key, "CHANGED", map1.get(key), map2.get(key)));
            } else {
                resultList.add(getMapChange(key, "UNCHANGED", map1.get(key)));
            }
        }
        return resultList;
    }

    private static Map<String, Object> getMapChange(String key, String status, Object value) {
        Map<String, Object> mapItem = new HashMap<>();
        mapItem.put("key", key);
        mapItem.put("status", status);
        mapItem.put("value", value);
        return mapItem;
    }

    private static Map<String, Object> getMapChange(String key, String status, Object oldValue, Object newValue) {
        Map<String, Object> mapItem = new HashMap<>();
        mapItem.put("key", key);
        mapItem.put("status", status);
        mapItem.put("oldValue", oldValue);
        mapItem.put("newValue", newValue);
        return mapItem;
    }
}

package hexlet.code;

import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    public static <T> String formatToStylish(List<T> resultList) {
        return resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }
}

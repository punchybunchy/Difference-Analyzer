package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Json {
    public static String getJsonFormat(List<Map<String, Object>> resultList) throws IOException {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.writeValueAsString(resultList);
        return result;
    }
}

package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Json {
    public static String getJsonFormat(Map<String, Object> diffResultMap) throws IOException {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("./src/main/resources/diff_file.json"), diffResultMap);
        result = objectMapper.writeValueAsString(diffResultMap);
        return result;
    }
}

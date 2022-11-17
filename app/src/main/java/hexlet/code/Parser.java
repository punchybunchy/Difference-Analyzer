package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {

    public static Map<String, Object> parsingToMap(String data, String type) throws Exception {
        return switch (type) {
            case ("json") -> jsonParsing(data);
            case ("yaml"), ("yml") -> yamlParsing(data);
            default -> throw new Exception("Not valid extension: " + type);
        };
    }

    private static Map<String, Object> jsonParsing(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, new TypeReference<>() { });
    }

    private static Map<String, Object> yamlParsing(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(data, new TypeReference<>() { });
    }

}

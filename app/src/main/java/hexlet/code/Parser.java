package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {

    public static Map<String, Object> parsingToMap(String dataFromFile, String fileExtension) throws Exception {
        return switch (fileExtension) {
            case ("json") -> jsonParsing(dataFromFile);
            case ("yaml"), ("yml") -> yamlParsing(dataFromFile);
            default -> throw new Exception("Not valid extension: " + fileExtension);
        };
    }

    private static Map<String, Object> jsonParsing(String dataFromFile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dataFromFile, new TypeReference<>() { });
    }

    private static Map<String, Object> yamlParsing(String dataFromFile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(dataFromFile, new TypeReference<>() { });
    }

}

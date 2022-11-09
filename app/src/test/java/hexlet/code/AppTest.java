package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private static final Map<String, Object> MAP_SAMPLE_1 = new LinkedHashMap<>();
    private static final Map<String, Object> MAP_SAMPLE_2 = new LinkedHashMap<>();

    private static final String JSON_FILE_PATH_1 =  "./src/test/resources/json_file1.json";
    private static final String JSON_FILE_PATH_2 =  "./src/test/resources/json_file2.json";
    private static final String YAML_FILE_PATH_1 =  "./src/test/resources/yaml_file1.yaml";
    private static final String YAML_FILE_PATH_2 =  "./src/test/resources/yaml_file2.yaml";

    @BeforeAll
    static void prepareSampleFiles() throws IOException {
        final int number1 = 2016;
        final int number2 = 2022;
        final int number3 = 29;
        final int number4 = 70;

        MAP_SAMPLE_1.put("squadName", "Super hero squad");
        MAP_SAMPLE_1.put("homeTown", "Metro City");
        MAP_SAMPLE_1.put("formed", number1);
        MAP_SAMPLE_1.put("secretBase", "Super tower");
        MAP_SAMPLE_1.put("active", true);
        MAP_SAMPLE_1.put("members", new LinkedHashMap<>() {{
                put("name", "Molecule Man");
                put("age", number3);
                put("secretIdentity", "Dan Jukes");
                }});
        MAP_SAMPLE_1.put("powers", List.of(
                "Radiation resistance",
                "Turning tiny",
                "Radiation blast"));

        MAP_SAMPLE_2.put("squadName", "Bastards");
        MAP_SAMPLE_2.put("homeTown", "Moscow City");
        MAP_SAMPLE_2.put("formed", number2);
        MAP_SAMPLE_2.put("secretBase", "Bunker");
        MAP_SAMPLE_2.put("reserved", false);
        MAP_SAMPLE_2.put("members", new LinkedHashMap<>() {{
                put("name", "Who");
                put("age", number4);
                put("secretIdentity", "Peesz Duke");
                }});
        MAP_SAMPLE_2.put("powers", List.of(
                "Radiation resistance",
                "Turning tiny",
                "Radiation blast"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(JSON_FILE_PATH_1), MAP_SAMPLE_1);
        objectMapper.writeValue(new File(JSON_FILE_PATH_2), MAP_SAMPLE_2);
        objectMapper.writeValue(new File(YAML_FILE_PATH_1), MAP_SAMPLE_1);
        objectMapper.writeValue(new File(YAML_FILE_PATH_2), MAP_SAMPLE_2);

    }
    @Test
    void testJsonParser() throws Exception {
        Path jsonFilePath = Paths.get(JSON_FILE_PATH_1).toAbsolutePath().normalize();
        String jsonFileData = Files.readString(jsonFilePath);
        Map<String, Object> actual = Parser.parsingToMap(jsonFileData, "json");
        var expected = MAP_SAMPLE_1.toString();
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void testYamlParser() throws Exception {
        Path yamlFilePath = Paths.get(YAML_FILE_PATH_1).toAbsolutePath().normalize();
        String yamlFileData = Files.readString(yamlFilePath);
        Map<String, Object> actual = Parser.parsingToMap(yamlFileData, "yaml");
        var expected = MAP_SAMPLE_1.toString();
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void testStylishDiffer() throws Exception {
        String expected =
                """
                        {
                          - active: true
                          - formed: 2016
                          + formed: 2022
                          - homeTown: Metro City
                          + homeTown: Moscow City
                          - members: {name=Molecule Man, age=29, secretIdentity=Dan Jukes}
                          + members: {name=Who, age=70, secretIdentity=Peesz Duke}
                            powers: [Radiation resistance, Turning tiny, Radiation blast]
                          + reserved: false
                          - secretBase: Super tower
                          + secretBase: Bunker
                          - squadName: Super hero squad
                          + squadName: Bastards
                        }""";

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "stylish");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "stylish");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testPlainDiffer() throws Exception {
        String expected =
                """
                        Property 'active' was removed
                        Property 'formed' was updated. From 2016 to 2022
                        Property 'homeTown' was updated. From 'Metro City' to 'Moscow City'
                        Property 'members' was updated. From [complex value] to [complex value]
                        Property 'reserved' was added with value: false
                        Property 'secretBase' was updated. From 'Super tower' to 'Bunker'
                        Property 'squadName' was updated. From 'Super hero squad' to 'Bastards'""";

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "plain");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "plain");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testJsonDiffer() throws Exception {

        final Path diffJsonPath =
                Paths.get("src/test/resources/diffJsonFormat.json").toAbsolutePath().normalize();
        final String diffJsonFormat = Files.readString(diffJsonPath);

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "json");
        assertThat(actualJson).isEqualTo(diffJsonFormat);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "json");
        assertThat(actualYaml).isEqualTo(diffJsonFormat);
    }
}

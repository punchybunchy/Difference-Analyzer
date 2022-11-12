package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static final String JSON_FILE_PATH_1 =  "./src/test/resources/json_file1.json";
    private static final String JSON_FILE_PATH_2 =  "./src/test/resources/json_file2.json";
    private static final String YAML_FILE_PATH_1 =  "./src/test/resources/yaml_file1.yaml";
    private static final String YAML_FILE_PATH_2 =  "./src/test/resources/yaml_file2.yaml";

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    static void readDiffFiles() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @Test
    void testStylishDiffer() throws Exception {
        String expected = resultStylish;

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "stylish");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "stylish");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testPlainDiffer() throws Exception {
        String expected = resultPlain;

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "plain");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "plain");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testJsonDiffer() throws Exception {

        String expected = resultJson;

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "json");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "json");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDefaultFormat() throws Exception {
        String expected = resultStylish;

        String actualJson = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2);
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2);
        assertThat(actualYaml).isEqualTo(expected);
    }
}

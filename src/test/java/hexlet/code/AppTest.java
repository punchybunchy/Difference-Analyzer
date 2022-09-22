package hexlet.code;

import org.junit.jupiter.api.Test;


import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AppTest {

    private final String filepath1 = "./src/test/resources/file1.yaml";
    private final String filepath2 = "./src/test/resources/file2.yaml";

    @Test
    public void testFileExistCheck() throws Exception {
        final String usersPathInput = "./src/test/resources/file1.yaml";

        boolean actual = Parser.fileExistCheck(usersPathInput);
        assertThat(actual).isTrue();
        System.out.println("testFileExistCheck ENDS");
    }
    @Test
    public void testNoFileThrowException() {
        final String wrongFilePath = "./src/test/resource/file1.yaml";

        Throwable thrown = catchThrowable(() -> {
            Parser.fileExistCheck(wrongFilePath);
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessage("File doesn't exist");
        System.out.println("testNoFileThrowException ENDS");
    }

    @Test
    public void testWrongExtensionFileThrowException() {
        final String wrongFileExtension = "./src/test/resources/file1.aml";

        Throwable thrown = catchThrowable(() -> {
            Parser.fileExistCheck(wrongFileExtension);
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessage("File exists, but has not valid extension");
        System.out.println("testWrongExtensionFileThrowException ENDS");
    }

    @Test
    public void testParserFilesToMap() throws Exception {
        Map<String, Object> expected = Map.of(
                "firstName", "Иван",
                "lastName", "Иванов",
                "address", "Московское ш., дом 10, кв.101",
                "city", "Ленинград",
                "postalCode", "101101");
        var actual = Parser.parserFilesToMap(filepath1);
        assertThat(actual).isEqualTo(expected);
        System.out.println("testParserFilesToMap ENDS");
    }

    @Test
    public void testDiffGenerate() throws Exception {
        String expected =
                """
                        {
                        - address: Московское ш., дом 10, кв.101
                        + address: Шкиперский проток, дом 20, кв.101
                        - city: Ленинград
                        + city: Санкт-Петербург
                          firstName: Иван
                          lastName: Иванов
                        + phone: +7-911-922-3344
                        - postalCode: 101101
                        }""";

        var actual = Differ.generate(Parser.parserFilesToMap(filepath1), Parser.parserFilesToMap(filepath2));
        assertThat(actual).isEqualTo(expected);
    }
}

package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AppTest {

    private final String filepath1 = "./src/test/resources/file3.yaml";
    private final String filepath2 = "./src/test/resources/file4.yaml";

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
    public void testStylishDiff() throws Exception {
        String formatName = "stylish";
        String expected = """
                {
                  - address: {улица=Московское ш., дом=10, квартира=101}
                  + address: {улица=Шкиперский проток, дом=20, квартира=101}
                    city: Санкт-Петербург
                    firstName: Иван
                  - lastName: Иванов
                  + lastName: Ванов
                  + phone: 79119223344
                  - postalCode: 101101
                }""";

        var actual = Differ.generate(filepath1, filepath2, formatName);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPlainDiff() throws Exception {
        String formatName = "plain";
        String expected = """
                Property 'address' was updated. From [complex value] to [complex value]
                Property 'lastName' was updated. From 'Иванов' to 'Ванов'
                Property 'phone' was added with value: 79119223344
                Property 'postalCode' was removed
                """;

        var actual = Differ.generate(filepath1, filepath2, formatName);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff() throws Exception {
        String formatName = "json";
        String expected = ""
                + "{\"- address\":{\"улица\":\"Московское ш.\",\"дом\":10,\"квартира\":101},"
                + "\"+ address\":{\"улица\":\"Шкиперский проток\",\"дом\":20,\"квартира\":101},"
                + "\"* city\":\"Санкт-Петербург\","
                + "\"* firstName\":\"Иван\","
                + "\"- lastName\":\"Иванов\","
                + "\"+ lastName\":\"Ванов\","
                + "\"++ phone\":79119223344,"
                + "\"-- postalCode\":101101}";

        var actual = Differ.generate(filepath1, filepath2, formatName);
        assertThat(actual).isEqualTo(expected);
    }
}

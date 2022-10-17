package hexlet.code;

import org.junit.jupiter.api.Test;


import java.util.List;
//import java.util.Map;


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

//    @Test
//    public void testParserFilesToMap() throws Exception {
//        Map<String, Object> expected = Map.of(
//                "firstName", "Иван",
//                "lastName", "Иванов",
//                "address", Map.of("дом",10,"квартира", 101, "улица","Московское ш."),
//                "city", "Санкт-Петербург",
//                "postalCode", 101101);
//        var actual = Parser.parserFilesToMap(filepath1);
//        assertThat(actual).isEqualTo(expected);
//        System.out.println("testParserFilesToMap ENDS");
//    }

    @Test
    public void testDiffGenerate() throws Exception {
        List<String> expected = List.of(
                "- address: {улица=Московское ш., дом=10, квартира=101}",
                "+ address: {улица=Шкиперский проток, дом=20, квартира=101}",
                "  city: Санкт-Петербург",
                "  firstName: Иван",
                "- lastName: Иванов",
                "+ lastName: Ванов",
                "+ phone: 79119223344",
                "- postalCode: 101101"
                );

        var actual = Differ.generate(Parser.parserFilesToMap(filepath1), Parser.parserFilesToMap(filepath2));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFormatter() {
        List<String> actualList = List.of(
                "- address: {улица=Московское ш., дом=10, квартира=101}",
                "+ address: {улица=Шкиперский проток, дом=20, квартира=101}",
                "  city: Санкт-Петербург",
                "  firstName: Иван",
                "- lastName: Иванов",
                "+ lastName: Ванов",
                "+ phone: 79119223344",
                "- postalCode: 101101"
        );
        String expected = "{\n  "
                + "- address: {улица=Московское ш., дом=10, квартира=101}\n  "
                + "+ address: {улица=Шкиперский проток, дом=20, квартира=101}\n  "
                + "  city: Санкт-Петербург\n  "
                + "  firstName: Иван\n  "
                + "- lastName: Иванов\n  "
                + "+ lastName: Ванов\n  "
                + "+ phone: 79119223344\n  "
                + "- postalCode: 101101\n"
                + "}";
        var actual = Formatter.formatToStylish(actualList);
        assertThat(actual).isEqualTo(expected);
    }
}

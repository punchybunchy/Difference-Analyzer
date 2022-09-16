package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private final String file1 = "./src/test/resources/filepath1.json";

    @Test
    public void testConvertJsonFileToMap() throws IOException {
        Map<String, Object> expected = Map.of(
                "firstName", "Иван",
                "lastName", "Иванов",
                "address", "Московское ш., дом 10, кв.101",
                "city", "Ленинград",
                "postalCode", "101101");
        var actual = App.convertJsonFileToMap(file1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDiffGenerate() throws IOException {
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
        String file2 = "./src/test/resources/filepath2.json";
        var actual = Differ.generate(App.convertJsonFileToMap(file1), App.convertJsonFileToMap(file2));
        assertThat(actual).isEqualTo(expected);
    }
}

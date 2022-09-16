package hexlet.code;
import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Option(names = { "-f", "--format" }, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", defaultValue = "./src/main/resources/filepath1.json",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", defaultValue = "./src/main/resources/filepath2.json",
            description = "path to second file")
    private String filepath2;

    public App() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws IOException {

        Map<String, Object> map1 = convertJsonFileToMap(filepath1);
        Map<String, Object> map2 = convertJsonFileToMap(filepath2);

        String diffResult = Differ.generate(map1, map2);
        System.out.println(diffResult);
        System.out.println(format);
        return 0;
    }

    public static Map<String, Object> convertJsonFileToMap(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(path.toFile(), new TypeReference<>() { });
        return map;
    }
}

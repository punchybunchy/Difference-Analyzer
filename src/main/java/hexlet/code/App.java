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

public class App implements Callable<Integer> {

    @Option(names = { "-f", "--format" }, paramLabel = "format",
            description = "output format [default: stylish]")
    String format;

    @Parameters(paramLabel = "filepath1", defaultValue = "/home/tyoma/app/src/main/resources/filepath1.json",
            description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", defaultValue = "/home/tyoma/app/src/main/resources/filepath2.json",
            description = "path to second file")
    String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws IOException {

        Path path1 = Paths.get(filepath1).toAbsolutePath();
        Path path2 = Paths.get(filepath2).toAbsolutePath();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(path1.toFile(), new TypeReference<>() {});
        Map<String, Object> map2 = objectMapper.readValue(path2.toFile(), new TypeReference<>() {});

        String diffResult = Differ.generate(map1, map2);
        System.out.println(diffResult);
        return 0;
    }
}

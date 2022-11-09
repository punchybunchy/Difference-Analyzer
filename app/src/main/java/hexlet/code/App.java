package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Option(names = { "-f", "--format" }, paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String formatName;

    @Parameters(paramLabel = "filepath1",
//            defaultValue = "./src/test/resources/yaml_file1.yaml",
            description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2",
//            defaultValue = "./src/test/resources/yaml_file2.yaml",
            description = "path to second file")
    private String filePath2;

    public App() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws Exception {

        String diffResult = Differ.generate(filePath1, filePath2, formatName);
        System.out.println(diffResult);
        return 0;
    }
}

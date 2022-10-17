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
    private String format;

    @Parameters(paramLabel = "filepath1",
            defaultValue = "./src/main/resources/file3.yaml",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2",
            defaultValue = "./src/main/resources/file4.yaml",
            description = "path to second file")
    private String filepath2;

    public App() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws Exception {

        String diffResult = Formatter.formatToStylish(Differ.generate(Parser.parserFilesToMap(filepath1),
                Parser.parserFilesToMap(filepath2)));
        System.out.println(diffResult);
        return 0;
    }
}

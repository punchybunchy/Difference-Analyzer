package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
//import picocli.CommandLine.Option;
//import picocli.CommandLine.Parameters;
//
//import java.io.File;
//import java.math.BigInteger;
//import java.nio.file.Files;
//import java.security.MessageDigest;
//import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public void run() {
        System.out.println("Hello, world!");
    }
}

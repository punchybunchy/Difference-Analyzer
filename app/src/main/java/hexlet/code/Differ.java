package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.MapsComparer.getMapsCompare;

public class Differ {

    public static String generate(String inputPath1, String inputPath2, String formatName) throws Exception {

        List<Map<String, Object>> resultList;

        Path filePath1 = getAbsolutePath(inputPath1);
        Path filePath2 = getAbsolutePath(inputPath2);

        String dataFromFile1 = getDataFromFile(filePath1);
        String dataFromFile2 = getDataFromFile(filePath2);

        String fileExtension1 = getFileExtension(filePath1);
        String fileExtension2 = getFileExtension(filePath2);

        Map<String, Object> map1 = Parser.parsingToMap(dataFromFile1, fileExtension1);
        Map<String, Object> map2 = Parser.parsingToMap(dataFromFile2, fileExtension2);

        resultList = getMapsCompare(map1, map2);
        return Formatter.formatSelection(formatName, resultList);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        String defaultFormatName = "stylish";
        return generate(filePath1, filePath2, defaultFormatName);
    }

    private static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static String getDataFromFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }

    private static String getFileExtension(Path filePath) {
        String extension = "";
        String fileName = filePath.getFileName().toString();
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}

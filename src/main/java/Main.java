
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import parser.HtmlParser;
import parser.WriterJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker INCORRECT_FILE_NAME = MarkerManager.getMarker("INCORRECT_FILE_NAME");

    private static final String URL_METRO = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";

    public static void main(String[] args) {

        try {
            HtmlParser htmlParser = new HtmlParser(URL_METRO);
            WriterJson.writeToJsonFile(htmlParser.getMetro(htmlParser.htmlFromURL()), userNameFileAndPathDirectory());
        } catch (IOException e) {
            LOGGER.catching(e);
        }
    }

    private static Path userNameFileAndPathDirectory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.println("Enter file name ending in .json");
            String fileName = reader.readLine();
            if (!fileName.endsWith(".json")) {
                System.out.println("Wrong file name.");
                LOGGER.warn(INCORRECT_FILE_NAME, "Incorrect file name: {}", fileName);
                continue;
            }
            LOGGER.info(INPUT_HISTORY_MARKER, "Correct file name: {}", fileName);
            System.out.println("Enter path");
            String directory = reader.readLine();
            if (directory.matches("")) {
                Path path = Path.of(directory + fileName);
                System.out.println("Json file created to root project directory.\n");
                return path;
            }
            else if (Path.of(directory).getParent() != null && Files.isWritable(Path.of(directory).getParent()) || Files.isWritable(Path.of(directory))) {
                Path path = Path.of(directory + "/" + fileName);
                System.out.println("Json file created to " + path + "\n");
                LOGGER.info(INPUT_HISTORY_MARKER, "Correct path: {}", directory);
                return path;
            }
            System.out.println("The specified path was not found");
            LOGGER.warn(INCORRECT_FILE_NAME, "Incorrect path: {}", directory);
        }
    }
}
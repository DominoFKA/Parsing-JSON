package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.nio.file.Path;
import java.io.IOException;

public class WriterJson {

    public static void writeToJsonFile(Metro metro, Path pathForSave) {

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter jsonFile = new FileWriter(pathForSave.toFile());
            jsonFile.write(gson.toJson(metro));
            jsonFile.flush();
            jsonFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
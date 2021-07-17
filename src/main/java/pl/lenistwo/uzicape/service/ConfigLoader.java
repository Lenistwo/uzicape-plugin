package pl.lenistwo.uzicape.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.lenistwo.uzicape.config.Config;
import pl.lenistwo.uzicape.exception.ConfigLoadException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class ConfigLoader {

    private final Gson gson;
    private final String filePath;

    public Config load() {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            return gson.fromJson(new String(fileBytes), Config.class);
        } catch (IOException e) {
            createPluginDirectory();
            createIfNotExist();
            throw new ConfigLoadException(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createPluginDirectory() {
        String path = filePath.substring(0, filePath.lastIndexOf(File.separator));
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void createIfNotExist() {
        File file = new File(filePath);
        if (file.exists()) {
            return;
        }

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file))) {
            Config config = new Config("https://uzicapes.pl/api/plugin/","81b7d57c-be3e-47c6-9faa-61b267389f9d",
                                       "cape", "check code",
                                       "/cape <CODE>", "Api Returned An Error");
            outputStreamWriter.write(gson.toJson(config));
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

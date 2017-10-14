package ru.sbt.mipt.oop.factory;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.domain.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GsonSmartHomeFactory implements SmartHomeFactory {
    @Override
    public SmartHome build() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));

        return gson.fromJson(json, SmartHome.class);
    }
}

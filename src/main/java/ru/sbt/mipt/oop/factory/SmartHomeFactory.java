package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.domain.SmartHome;

import java.io.IOException;

public interface SmartHomeFactory {
    SmartHome create() throws IOException;
}

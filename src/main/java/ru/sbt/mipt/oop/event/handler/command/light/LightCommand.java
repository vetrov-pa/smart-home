package ru.sbt.mipt.oop.event.handler.command.light;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.SmartHome;

public interface LightCommand {
    void execute(SmartHome smartHome, Light light);
}

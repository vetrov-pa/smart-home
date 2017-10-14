package ru.sbt.mipt.oop.event.handler.state;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.SmartHome;

public interface LightState {
    void apply(SmartHome smartHome, Light light);
}

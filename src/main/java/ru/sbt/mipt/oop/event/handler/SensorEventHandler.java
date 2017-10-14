package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEventType;

public interface SensorEventHandler {
    void handle(SmartHome smartHome, SensorEvent sensorEvent);

    boolean isSupported(SensorEventType sensorEventType);
}

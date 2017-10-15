package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;

import java.util.Iterator;

public interface SmartHomeEventHandler {
    void handle(SmartHome smartHome, Iterator<SensorEvent> sensorEventIterator);
}

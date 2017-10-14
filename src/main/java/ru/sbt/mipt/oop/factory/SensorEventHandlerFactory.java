package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.event.handler.SensorEventHandler;

public interface SensorEventHandlerFactory {
    SensorEventHandler create();
}

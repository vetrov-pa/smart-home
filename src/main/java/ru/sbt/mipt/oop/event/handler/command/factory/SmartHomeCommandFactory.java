package ru.sbt.mipt.oop.event.handler.command.factory;

import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.door.DoorCommand;
import ru.sbt.mipt.oop.event.handler.command.light.LightCommand;

public interface SmartHomeCommandFactory {
    DoorCommand createDoorCommand(SensorEventType sensorEventType);

    LightCommand createLightCommand(SensorEventType sensorEventType);
}

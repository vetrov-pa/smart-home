package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.door.DoorCommand;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactory;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactoryImpl;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final SmartHomeCommandFactory homeCommandFactory = SmartHomeCommandFactoryImpl.getInstance();

    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        String doorId = sensorEvent.getObjectId();

        Door door = SmartHomeService.getDoor(smartHome, doorId);
        if (door == null) {
            throw new IllegalArgumentException("Door " + doorId + " not found");
        }

        DoorCommand doorCommand = homeCommandFactory.createDoorCommand(sensorEvent.getType());

        doorCommand.execute(smartHome, door);
    }
}

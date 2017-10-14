package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.light.LightCommand;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactory;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactoryImpl;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;

public class CloseHallDoorSensorEventHandler implements SensorEventHandler {
    private final SmartHomeCommandFactory homeCommandFactory = SmartHomeCommandFactoryImpl.getInstance();

    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return sensorEventType == DOOR_CLOSED;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        String doorId = sensorEvent.getObjectId();

        Door door = SmartHomeService.getDoor(smartHome, doorId);
        if (door == null) {
            throw new IllegalArgumentException("Door " + doorId + " not found");
        }

        Room room = SmartHomeService.getDoorRoom(smartHome, doorId);

        if (!room.getName().equals("hall")) return;

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                LightCommand lightCommand = homeCommandFactory.createLightCommand(LIGHT_OFF);
                lightCommand.execute(smartHome, light);
            }
        }
    }
}

package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.iterator.LightIterator;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.light.LightCommand;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactory;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactoryImpl;

import java.util.Iterator;

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
        Room room = SmartHomeService.getDoorRoom(smartHome, sensorEvent.getObjectId());
        if (!room.getName().equals("hall")) return;

        Iterator<Light> lightIterator = new LightIterator(smartHome);
        while (lightIterator.hasNext()) {
            Light light = lightIterator.next();

            LightCommand lightCommand = homeCommandFactory.createLightCommand(LIGHT_OFF);
            lightCommand.execute(smartHome, light);
        }
    }
}

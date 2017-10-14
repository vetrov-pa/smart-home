package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.state.LightOffState;
import ru.sbt.mipt.oop.event.handler.state.LightState;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;

public class CloseHallDoorSensorEventHandler implements SensorEventHandler {
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
                LightState lightState = new LightOffState();
                lightState.apply(smartHome, light);
            }
        }
    }
}

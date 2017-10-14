package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.state.DoorCloseState;
import ru.sbt.mipt.oop.event.handler.state.DoorState;
import ru.sbt.mipt.oop.event.handler.state.DoorOpenState;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorSensorEventHandler implements SensorEventHandler {
    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        // событие от двери
        String doorId = sensorEvent.getObjectId();

        Door door = SmartHomeService.getDoor(smartHome, doorId);
        if (door == null) {
            throw new IllegalArgumentException("Door " + doorId + " not found");
        }

        SensorEventType sensorEventType = sensorEvent.getType();
        DoorState doorState = null;
        if (sensorEventType == DOOR_OPEN) {
            doorState = new DoorOpenState();
        } else if (sensorEventType == DOOR_CLOSED) {
            doorState = new DoorCloseState();
        }

        if (doorState == null) {
            throw new IllegalStateException("Invalid Door state " + sensorEventType.name());
        }

        doorState.apply(smartHome, door);
    }
}

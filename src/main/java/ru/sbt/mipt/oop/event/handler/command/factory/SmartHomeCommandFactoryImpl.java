package ru.sbt.mipt.oop.event.handler.command.factory;

import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.door.DoorCloseCommand;
import ru.sbt.mipt.oop.event.handler.command.door.DoorCommand;
import ru.sbt.mipt.oop.event.handler.command.door.DoorOpenCommand;
import ru.sbt.mipt.oop.event.handler.command.light.LightCommand;
import ru.sbt.mipt.oop.event.handler.command.light.LightOffCommand;
import ru.sbt.mipt.oop.event.handler.command.light.LightOnCommand;

import static ru.sbt.mipt.oop.event.SensorEventType.*;

public class SmartHomeCommandFactoryImpl implements SmartHomeCommandFactory {
    private static SmartHomeCommandFactory smartHomeCommandFactory = new SmartHomeCommandFactoryImpl();

    private SmartHomeCommandFactoryImpl() {
    }

    public static SmartHomeCommandFactory getInstance() {
        return smartHomeCommandFactory;
    }

    @Override
    public DoorCommand createDoorCommand(SensorEventType sensorEventType) {
        DoorCommand doorCommand = null;

        if (sensorEventType == DOOR_OPEN) {
            doorCommand = new DoorOpenCommand();
        } else if (sensorEventType == DOOR_CLOSED) {
            doorCommand = new DoorCloseCommand();
        }

        if (doorCommand == null) {
            throw new IllegalStateException("Invalid Door command " + sensorEventType.name());
        }

        return doorCommand;
    }

    @Override
    public LightCommand createLightCommand(SensorEventType sensorEventType) {
        LightCommand lightCommand = null;
        if (sensorEventType == LIGHT_ON) {
            lightCommand = new LightOnCommand();
        } else if (sensorEventType == LIGHT_OFF) {
            lightCommand = new LightOffCommand();
        }

        if (lightCommand == null) {
            throw new IllegalStateException("Invalid Light command " + sensorEventType.name());
        }

        return lightCommand;
    }
}

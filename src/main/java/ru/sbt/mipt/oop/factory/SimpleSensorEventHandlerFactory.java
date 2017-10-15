package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.event.handler.*;

import static java.util.Arrays.asList;

public class SimpleSensorEventHandlerFactory implements SensorEventHandlerFactory {
    private static SimpleSensorEventHandlerFactory factory = new SimpleSensorEventHandlerFactory();

    private SimpleSensorEventHandlerFactory() {
    }

    public static SimpleSensorEventHandlerFactory getInstance() {
        return factory;
    }

    @Override
    public SensorEventHandler create() {
        return new SensorEventHandlerComposite(asList(
                new LightSensorEventHandler()
                , new DoorSensorEventHandler()
                , new CloseHallDoorSensorEventHandler()
        ));
    }
}

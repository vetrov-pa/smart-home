package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.event.handler.*;

import static java.util.Arrays.asList;

public class SimpleSensorEventHandlerFactory implements SensorEventHandlerFactory {
    @Override
    public SensorEventHandler create() {
        return new SensorEventHandlerComposite(asList(
                new LightSensorEventHandler()
                , new DoorSensorEventHandler()
                , new CloseHallDoorSensorEventHandler()
        ));
    }
}

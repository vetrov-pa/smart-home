package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.factory.SensorEventHandlerFactory;
import ru.sbt.mipt.oop.factory.SimpleSensorEventHandlerFactory;

import java.util.Iterator;

public class SmartHomeEventHandlerImpl implements SmartHomeEventHandler {
    @Override
    public void handle(SmartHome smartHome, Iterator<SensorEvent> sensorEventIterator) {
        SensorEventHandlerFactory eventHandlerFactory = new SimpleSensorEventHandlerFactory();
        SensorEventHandler sensorEventHandler = eventHandlerFactory.create();

        while (sensorEventIterator.hasNext()) {
            SensorEvent event = sensorEventIterator.next();

            System.out.println("Got event: " + event);

            sensorEventHandler.handle(smartHome, event);
        }
    }
}

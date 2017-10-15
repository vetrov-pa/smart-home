package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.factory.SensorEventHandlerFactory;
import ru.sbt.mipt.oop.factory.SimpleSensorEventHandlerFactory;

import java.util.Iterator;

public class IterableSmartHomeEventHandler implements SmartHomeEventHandler {
    private final Iterator<SensorEvent> sensorEventIterator;
    private final SensorEventHandlerFactory eventHandlerFactory = SimpleSensorEventHandlerFactory.getInstance();
    private final SensorEventHandler sensorEventHandler;

    public IterableSmartHomeEventHandler(Iterator<SensorEvent> sensorEventIterator) {
        this.sensorEventIterator = sensorEventIterator;
        this.sensorEventHandler = eventHandlerFactory.create();
    }

    @Override
    public void handle(SmartHome smartHome) {
        while (sensorEventIterator.hasNext()) {
            SensorEvent event = sensorEventIterator.next();

            System.out.println("Got event: " + event);

            sensorEventHandler.handle(smartHome, event);
        }
    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventIterator;
import ru.sbt.mipt.oop.event.handler.*;
import ru.sbt.mipt.oop.factory.GsonSmartHomeFactory;
import ru.sbt.mipt.oop.factory.SensorEventHandlerFactory;
import ru.sbt.mipt.oop.factory.SimpleSensorEventHandlerFactory;
import ru.sbt.mipt.oop.factory.SmartHomeFactory;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        SmartHomeFactory smartHomeFactory = new GsonSmartHomeFactory();
        SmartHome smartHome = smartHomeFactory.create();

        SensorEventHandlerFactory eventHandlerFactory = new SimpleSensorEventHandlerFactory();
        SensorEventHandler sensorEventHandler = eventHandlerFactory.create();

        SensorEventIterator sensorEventIterator = new SensorEventIterator();
        while (sensorEventIterator.hasNext()) {
            SensorEvent event = sensorEventIterator.next();

            System.out.println("Got event: " + event);

            sensorEventHandler.handle(smartHome, event);
        }
    }
}

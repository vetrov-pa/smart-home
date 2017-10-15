package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.RandomSensorEventIterator;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.handler.*;
import ru.sbt.mipt.oop.factory.GsonSmartHomeFactory;
import ru.sbt.mipt.oop.factory.SmartHomeFactory;

import java.io.IOException;
import java.util.Iterator;

public class Application {
    public static void main(String... args) throws IOException {
        SmartHomeFactory smartHomeFactory = new GsonSmartHomeFactory();
        SmartHome smartHome = smartHomeFactory.create();

        Iterator<SensorEvent> randomSensorEventIterator = new RandomSensorEventIterator();

        SmartHomeEventHandler smartHomeEventHandler = new IterableSmartHomeEventHandler(randomSensorEventIterator);
        smartHomeEventHandler.handle(smartHome);
    }
}

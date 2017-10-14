package ru.sbt.mipt.oop.event;

import java.util.Iterator;

public class SensorEventIterator implements Iterator<SensorEvent> {
    @Override
    public boolean hasNext() {
        return Math.random() >= 0.05;
    }

    @Override
    public SensorEvent next() {
        SensorEventType sensorEventType = getRandomSensorEventType();
        String objectId = getRandomObjectId();

        return new SensorEvent(sensorEventType, objectId);
    }

    private String getRandomObjectId() {
        return "" + ((int) (10 * Math.random()));
    }

    private SensorEventType getRandomSensorEventType() {
        return SensorEventType.values()[(int) (4 * Math.random())];
    }
}

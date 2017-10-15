package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.SensorEventType;

import java.util.List;

public class SensorEventHandlerComposite implements SensorEventHandler {
    private final List<SensorEventHandler> eventHandlerList;

    public SensorEventHandlerComposite(List<SensorEventHandler> eventHandlerList) {
        this.eventHandlerList = eventHandlerList;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        eventHandlerList.stream()
                .filter(handler -> handler.isSupported(sensorEvent.getType()))
                .forEach(handler -> {
                    try {
                        handler.handle(smartHome, sensorEvent);
                    } catch (Exception ex) {
                        System.out.println("error: " + ex.getMessage());
                    }
                });
    }

    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return true;
    }
}

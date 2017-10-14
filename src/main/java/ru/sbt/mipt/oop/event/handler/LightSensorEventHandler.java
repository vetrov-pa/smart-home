package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.command.light.LightCommand;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactory;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactoryImpl;

import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements SensorEventHandler {
    private final SmartHomeCommandFactory homeCommandFactory = SmartHomeCommandFactoryImpl.getInstance();

    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return sensorEventType == LIGHT_ON || sensorEventType == LIGHT_OFF;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        String lightId = sensorEvent.getObjectId();
        Light light = SmartHomeService.getLight(smartHome, lightId);
        if (light == null) {
            throw new IllegalArgumentException("Light " + lightId + " not found");
        }

        LightCommand lightCommand = homeCommandFactory.createLightCommand(sensorEvent.getType());

        lightCommand.execute(smartHome, light);
    }
}

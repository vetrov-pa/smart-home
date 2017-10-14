package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.event.handler.state.LightState;
import ru.sbt.mipt.oop.event.handler.state.LightOffState;
import ru.sbt.mipt.oop.event.handler.state.LightOnState;

import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements SensorEventHandler {
    @Override
    public boolean isSupported(SensorEventType sensorEventType) {
        return sensorEventType == LIGHT_ON || sensorEventType == LIGHT_OFF;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent sensorEvent) {
        // событие от источника света
        String lightId = sensorEvent.getObjectId();
        Light light = SmartHomeService.getLight(smartHome, lightId);
        if (light == null) {
            throw new IllegalArgumentException("Light " + lightId + " not found");
        }

        SensorEventType sensorEventType = sensorEvent.getType();
        LightState lightState = null;
        if (sensorEventType == LIGHT_ON) {
            lightState = new LightOnState();
        } else if (sensorEventType == LIGHT_OFF) {
            lightState = new LightOffState();
        }

        if (lightState == null) {
            throw new IllegalStateException("Invalid Light state " + sensorEventType.name());
        }

        lightState.apply(smartHome, light);
    }
}

package ru.sbt.mipt.oop.event.handler.state;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;

public class LightOnState implements LightState {
    @Override
    public void apply(SmartHome smartHome, Light light) {
        Room room = SmartHomeService.getLightRoom(smartHome, light.getId());

        light.setOn(true);

        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }
}

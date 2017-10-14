package ru.sbt.mipt.oop.event.handler.command.light;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;

public class LightOnCommand implements LightCommand {
    @Override
    public void execute(SmartHome smartHome, Light light) {
        Room room = SmartHomeService.getLightRoom(smartHome, light.getId());

        light.setOn(true);

        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }
}

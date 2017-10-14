package ru.sbt.mipt.oop.event.handler.command.door;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;

public class DoorCloseCommand implements DoorCommand {
    @Override
    public void execute(SmartHome smartHome, Door door) {
        Room room = SmartHomeService.getDoorRoom(smartHome, door.getId());

        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }
}

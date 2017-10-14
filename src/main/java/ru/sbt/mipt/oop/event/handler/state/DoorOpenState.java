package ru.sbt.mipt.oop.event.handler.state;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.domain.service.SmartHomeService;

public class DoorOpenState implements DoorState {
    @Override
    public void apply(SmartHome smartHome, Door door) {
        Room room = SmartHomeService.getDoorRoom(smartHome, door.getId());

        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }
}

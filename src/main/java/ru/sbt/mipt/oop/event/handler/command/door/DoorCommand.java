package ru.sbt.mipt.oop.event.handler.command.door;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.SmartHome;

public interface DoorCommand {
    void execute(SmartHome smartHome, Door door);
}

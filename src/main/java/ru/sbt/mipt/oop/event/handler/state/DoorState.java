package ru.sbt.mipt.oop.event.handler.state;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.SmartHome;

public interface DoorState {
    void apply(SmartHome smartHome, Door door);
}

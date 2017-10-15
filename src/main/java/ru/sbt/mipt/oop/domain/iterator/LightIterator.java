package ru.sbt.mipt.oop.domain.iterator;

import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;
import ru.sbt.mipt.oop.event.handler.command.factory.SmartHomeCommandFactoryImpl;

import java.util.Iterator;

public class LightIterator implements Iterator<Light> {
    private Iterator<Room> roomIterator;
    private Iterator<Light> lightIterator;

    public LightIterator(SmartHome smartHome) {
        roomIterator = smartHome.getRooms().iterator();
        if (!roomIterator.hasNext()) {
            throw new IllegalStateException("There should be at least one room is Smart Home");
        }

        lightIterator = roomIterator.next().getLights().iterator();
    }

    @Override
    public boolean hasNext() {
        return lightIterator.hasNext() || roomIterator.hasNext();
    }

    @Override
    public Light next() {
        if (!lightIterator.hasNext()) {
            lightIterator = roomIterator.next().getLights().iterator();
        }

        return lightIterator.next();
    }
}

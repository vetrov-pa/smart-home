package ru.sbt.mipt.oop.domain.service;

import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.domain.SmartHome;

public class SmartHomeService {
    public static Door getDoor(SmartHome smartHome, String doorId) {
        Door door = null;
        for (Room room : smartHome.getRooms()) {
            door = getDoor(room, doorId);

            if (door != null) break;
        }

        return door;
    }

    public static Light getLight(SmartHome smartHome, String lightId) {
        Light light = null;
        for (Room room : smartHome.getRooms()) {
            light = getLight(room, lightId);

            if (light != null) break;
        }

        return light;
    }

    public static Room getLightRoom(SmartHome smartHome, String lightId) {
        Room targetRoom = null;
        for (Room room : smartHome.getRooms()) {
            if (getLight(room, lightId) != null){
                targetRoom = room;
                break;
            }
        }

        return targetRoom;
    }

    private static Light getLight(Room room, String lightId) {
        Light targetLight = null;

        for (Light light : room.getLights()) {
            if (light.getId().equals(lightId)) {
                targetLight = light;
                break;
            }
        }

        return targetLight;
    }

    private static Door getDoor(Room room, String doorId) {
        Door targetDoor = null;

        for (Door door : room.getDoors()) {
            if (door.getId().equals(doorId)) {
                targetDoor = door;
                break;
            }
        }

        return targetDoor;
    }

    public static Room getDoorRoom(SmartHome smartHome, String doorId) {
        Room targetRoom = null;
        for (Room room : smartHome.getRooms()) {
            if (getDoor(room, doorId) != null){
                targetRoom = room;
                break;
            }
        }

        return targetRoom;
    }
}

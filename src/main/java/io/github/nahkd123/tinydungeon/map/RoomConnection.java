package io.github.nahkd123.tinydungeon.map;

import io.github.nahkd123.tinydungeon.feature.DoorFeature;
import io.github.nahkd123.tinydungeon.room.Room;

public record RoomConnection(Room fromRoom, DoorFeature fromDoor, Room toRoom, DoorFeature toDoor) {
}

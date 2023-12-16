package io.github.nahkd123.tinydungeon.map;

import io.github.nahkd123.tinydungeon.feature.DoorFeature;
import io.github.nahkd123.tinydungeon.room.Room;

/**
 * <p>
 * Represents the mutable dungeon map.
 * </p>
 * <p>
 * Mutable maps are used in dungeon generators to generate dungeon. The
 * implementation may choose to use {@link MutableDungeonMap} as "template" and
 * {@link DungeonMap} as "in-game dungeon data".
 * </p>
 */
public interface MutableDungeonMap extends DungeonMap {
	public void addRoom(Room room);

	public boolean removeRoom(Room room);

	public void addConnection(Room fromRoom, DoorFeature fromDoor, Room toRoom, DoorFeature toDoor);

	public boolean removeConnection(Room fromRoom, DoorFeature fromDoor, Room toRoom, DoorFeature toDoor);
}

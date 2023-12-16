package io.github.nahkd123.tinydungeon.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import io.github.nahkd123.tinydungeon.feature.DoorFeature;
import io.github.nahkd123.tinydungeon.room.Room;
import io.github.nahkd123.tinydungeon.util.Box;

public class SimpleMutableDungeonMap implements MutableDungeonMap {
	private List<Room> rooms = new ArrayList<>();
	private List<RoomConnection> connections = new ArrayList<>();

	@Override
	public Optional<Room> getRoomAt(int x, int y) {
		for (Room room : rooms) if (room.isIntersectWith(x, y)) return Optional.of(room);
		return Optional.empty();
	}

	@Override
	public Optional<Room> findCollision(Box box) {
		for (Room room : rooms) if (room.isIntersectWith(box)) return Optional.of(room);
		return Optional.empty();
	}

	@Override
	public List<Room> getAllRooms() { return Collections.unmodifiableList(rooms); }

	@Override
	public void addRoom(Room room) {
		if (!rooms.contains(room)) rooms.add(room);
	}

	@Override
	public boolean removeRoom(Room room) {
		return rooms.remove(room);
	}

	@Override
	public void addConnection(Room fromRoom, DoorFeature fromDoor, Room toRoom, DoorFeature toDoor) {
		connections.add(new RoomConnection(fromRoom, fromDoor, toRoom, toDoor));
	}

	@Override
	public boolean removeConnection(Room fromRoom, DoorFeature fromDoor, Room toRoom, DoorFeature toDoor) {
		Iterator<RoomConnection> iter = connections.iterator();

		while (iter.hasNext()) {
			RoomConnection conn = iter.next();

			if (conn.fromRoom() == fromRoom &&
				conn.fromDoor() == fromDoor &&
				conn.toRoom() == toRoom &&
				conn.toDoor() == toDoor) {
				iter.remove();
				return true;
			}
		}

		return false;
	}

	@Override
	public List<RoomConnection> getAllConnections() { return Collections.unmodifiableList(connections); }
}

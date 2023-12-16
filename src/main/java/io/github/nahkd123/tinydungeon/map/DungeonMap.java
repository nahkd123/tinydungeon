package io.github.nahkd123.tinydungeon.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import io.github.nahkd123.tinydungeon.room.Room;
import io.github.nahkd123.tinydungeon.util.Box;
import io.github.nahkd123.tinydungeon.util.RectangularBox;

/**
 * <p>
 * Represents the dungeon map.
 * </p>
 * <p>
 * <b>Entrance:</b> The entrance is <b>always</b> placed at 0:0, or map origin.
 * </p>
 */
public interface DungeonMap {
	public Optional<Room> getRoomAt(int x, int y);

	/**
	 * <p>
	 * Check if the box collides with at least 1 room in the dungeon, returns the
	 * collided room.
	 * </p>
	 * <p>
	 * Note that {@link Room} extends {@link Box} interface so you can use that. If
	 * you want to test collision in a custom area, use {@link RectangularBox}.
	 * </p>
	 * 
	 * @param box The box to test for collision.
	 * @return Collided room.
	 */
	public Optional<Room> findCollision(Box box);

	/**
	 * <p>
	 * Get all rooms in this map.
	 * </p>
	 * <p>
	 * The returned list must be unmodifiable.
	 * </p>
	 * 
	 * @return Unmodifiable list of rooms in this map.
	 */
	public List<Room> getAllRooms();

	public List<RoomConnection> getAllConnections();

	default List<RoomConnection> findAllConnectionsFrom(Room fromRoom) {
		return getAllConnections().stream().filter(v -> v.fromRoom() == fromRoom).toList();
	}

	default RectangularBox calculateBoundingBox() {
		List<Room> rooms = getAllRooms();
		RectangularBox bb = new RectangularBox(rooms.get(0));

		for (int i = 1; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			bb = bb.expand(room);
		}

		return bb;
	}

	/**
	 * <p>
	 * Attempt to print the debugging information to logger.
	 * </p>
	 * <p>
	 * Because this is for debugging only, you can't customize its output, unless
	 * you decided to modify it with your custom logger (which is kinda dumb).
	 * </p>
	 * 
	 * @param logger Your "logger" that consumes debugging lines and (maybe) print
	 *               it to console/terminal.
	 */
	default void printDebugInfo(Consumer<String> logger) {
		RectangularBox bb = calculateBoundingBox();
		List<Room> rooms = getAllRooms();
		String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&()[]{};:=+";
		Map<Room, Character> mappedSymbols = new HashMap<>();

		logger.accept("Dungeon debug info:");
		logger.accept(" - Size: " + bb.getSize().x() + " x " + bb.getSize().y() + " units sq.");
		logger.accept(" - Dungeon corner: " + bb.getPosition());
		logger.accept(" - Rooms count: " + rooms.size());
		logger.accept(" - Dungeon map:");

		for (int ly = 0; ly < bb.getSize().y(); ly++) {
			int y = ly + bb.getPosition().y();
			String line = "";

			for (int lx = 0; lx < bb.getSize().x(); lx++) {
				int x = lx + bb.getPosition().x();
				Optional<Room> room = getRoomAt(x, y);

				if (room.isEmpty()) {
					line += "  ";
					continue;
				}

				Character symbol = mappedSymbols.get(room.get());
				if (symbol == null) mappedSymbols.put(room.get(), symbol = symbols.charAt(mappedSymbols.size()));
				line += symbol + "" + symbol;
			}

			logger.accept("%2d | %s".formatted(ly, line));
		}

		logger.accept(" - Legends:");

		for (Entry<Room, Character> entry : mappedSymbols.entrySet()) {
			logger.accept("   " + entry.getValue() + ": " + entry.getKey());
		}
	}
}

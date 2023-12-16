package io.github.nahkd123.tinydungeon.feature;

import io.github.nahkd123.tinydungeon.room.RoomFactory;
import io.github.nahkd123.tinydungeon.util.Vector2;

/**
 * <p>
 * Represent a feature in a dungeon room.
 * </p>
 * <p>
 * A dungeon room can have multiple features, and these features can be placed
 * randomly by {@link RoomFactory}, or fixed in one place in the room. Features'
 * positions are relative to the room origin, so if the room's position is
 * {@code (1, 1)} and the feature's {@link #getPosition()} is {@code (2, 2)},
 * the feature's global/world position is {@code (3, 3)}.
 * </p>
 */
public interface Feature {
	/**
	 * <p>
	 * Get position of this dungeon room feature, relative to room position.
	 * </p>
	 * 
	 * @return The position of this feature relative to the room position.
	 */
	public Vector2 getPosition();
}

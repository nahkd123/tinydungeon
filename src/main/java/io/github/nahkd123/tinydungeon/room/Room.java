package io.github.nahkd123.tinydungeon.room;

import java.util.List;

import io.github.nahkd123.tinydungeon.feature.Feature;
import io.github.nahkd123.tinydungeon.util.Box;
import io.github.nahkd123.tinydungeon.util.Vector2;

/**
 * <p>
 * Represent a room in a dungeon. Note that this room instance will be used by
 * the game controller while the dungeon is active.
 * </p>
 * <p>
 * A dungeon is said to be active if there is at least 1 player playing the
 * dungeon, or the dungeon was generated ahead of time and waiting for players.
 * </p>
 */
public interface Room extends Box {
	public void setPosition(Vector2 position);

	default void setPosition(int x, int y) {
		setPosition(new Vector2(x, y));
	}

	/**
	 * <p>
	 * Get a list of features belong to this room. This will be assigned by the room
	 * factory. Features' position are placed relative to the room's
	 * {@link #getPosition()}.
	 * </p>
	 * <p>
	 * Implementation must return an unmodifiable version of the list.
	 * </p>
	 * 
	 * @return List of features.
	 */
	public List<Feature> getFeatures();
}

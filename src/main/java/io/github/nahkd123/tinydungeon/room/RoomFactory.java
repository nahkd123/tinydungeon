package io.github.nahkd123.tinydungeon.room;

import io.github.nahkd123.tinydungeon.generate.GenerationContext;

/**
 * <p>
 * Create rooms for the generator. Each room created from this factory can be
 * varies in size and number of features.
 * </p>
 * <p>
 * The dungeon generator may create multiple rooms at once to act as a "buffer".
 * </p>
 */
public interface RoomFactory {
	/**
	 * <p>
	 * Create a new dungeon room with its position set to {@code(0, 0)}.
	 * </p>
	 * 
	 * @param context The context that should be used across all room creations.
	 * @return New room.
	 */
	public Room createNew(GenerationContext context);
}

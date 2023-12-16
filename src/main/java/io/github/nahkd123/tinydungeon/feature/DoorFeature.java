package io.github.nahkd123.tinydungeon.feature;

import io.github.nahkd123.tinydungeon.generate.JigsawDungeonGenerator;
import io.github.nahkd123.tinydungeon.util.Face;

/**
 * <p>
 * A special room feature: the door.
 * </p>
 * <p>
 * Doors are used to connect between 2 rooms. If the room does not have a door,
 * it will never be generated. If the room without doors happens to be the
 * entrance for {@link JigsawDungeonGenerator}, the generator will fails.
 * </p>
 */
public interface DoorFeature extends Feature {
	/**
	 * <p>
	 * Get the door facing that is facing outwards the room.
	 * </p>
	 * 
	 * @return The door facing direction.
	 */
	public Face getFacing();

	/**
	 * <p>
	 * Get the ID of the key that will be used to unlock this door.
	 * </p>
	 * <p>
	 * This method may return {@code null} to indicate the door should be opened
	 * automatically.
	 * </p>
	 * 
	 * @return The ID of the unlock key for this door.
	 */
	public String getUnlockKeyId();
}

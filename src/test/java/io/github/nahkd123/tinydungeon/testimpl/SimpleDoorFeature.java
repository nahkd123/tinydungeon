package io.github.nahkd123.tinydungeon.testimpl;

import io.github.nahkd123.tinydungeon.feature.DoorFeature;
import io.github.nahkd123.tinydungeon.util.Face;
import io.github.nahkd123.tinydungeon.util.Vector2;

public class SimpleDoorFeature implements DoorFeature {
	private Vector2 position;
	private Face facing;
	private String unlockId;

	public SimpleDoorFeature(Vector2 position, Face facing, String unlockId) {
		this.position = position;
		this.facing = facing;
		this.unlockId = unlockId;
	}

	@Override
	public Vector2 getPosition() { return position; }

	@Override
	public Face getFacing() { return facing; }

	@Override
	public String getUnlockKeyId() { return unlockId; }

	@Override
	public String toString() {
		return "SimpleDoorFeature[" + position + " -> " + facing + "; unlockId = " + unlockId + "]";
	}
}

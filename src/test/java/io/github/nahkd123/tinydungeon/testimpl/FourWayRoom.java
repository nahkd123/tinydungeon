package io.github.nahkd123.tinydungeon.testimpl;

import java.util.ArrayList;
import java.util.List;

import io.github.nahkd123.tinydungeon.feature.Feature;
import io.github.nahkd123.tinydungeon.room.Room;
import io.github.nahkd123.tinydungeon.room.RoomFactory;
import io.github.nahkd123.tinydungeon.util.Face;
import io.github.nahkd123.tinydungeon.util.Vector2;

public class FourWayRoom implements Room {
	public static final RoomFactory FACTORY = context -> new FourWayRoom(context.getRandom().nextInt(3) * 2 + 3);

	private int x, y;
	private int size;
	private List<Feature> features = new ArrayList<>();

	public FourWayRoom(int size) {
		this.size = size;
		features.add(new SimpleDoorFeature(new Vector2(size - 1, size / 2), Face.POSITIVE_X, "door"));
		features.add(new SimpleDoorFeature(new Vector2(0, size / 2), Face.NEGATIVE_X, "door"));
		features.add(new SimpleDoorFeature(new Vector2(size / 2, size - 1), Face.POSITIVE_Y, "door"));
		features.add(new SimpleDoorFeature(new Vector2(size / 2, 0), Face.NEGATIVE_Y, "door"));
	}

	@Override
	public Vector2 getPosition() { return new Vector2(x, y); }

	@Override
	public Vector2 getSize() { return new Vector2(size, size); }

	@Override
	public void setPosition(Vector2 position) {
		this.x = position.x();
		this.y = position.y();
	}

	@Override
	public List<Feature> getFeatures() { return features; }

	@Override
	public String toString() {
		return "FourWayRoom[" + x + ":" + y + "; size=" + size + "]";
	}
}

package io.github.nahkd123.tinydungeon.util;

/**
 * <p>
 * The facing direction.
 * </p>
 * <p>
 * This will be used for dungeon doors placements.
 * </p>
 */
public enum Face {
	POSITIVE_X(new Vector2(1, 0)),
	NEGATIVE_X(new Vector2(-1, 0)),
	POSITIVE_Y(new Vector2(0, 1)),
	NEGATIVE_Y(new Vector2(0, -1));

	private Vector2 vector;

	private Face(Vector2 vector) {
		this.vector = vector;
	}

	public Vector2 getVector() { return vector; }

	public Face getOpposite() {
		return switch (this) {
		case POSITIVE_X -> NEGATIVE_X;
		case NEGATIVE_X -> POSITIVE_X;
		case POSITIVE_Y -> NEGATIVE_Y;
		case NEGATIVE_Y -> POSITIVE_Y;
		default -> POSITIVE_X;
		};
	}
}

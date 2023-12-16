package io.github.nahkd123.tinydungeon.util;

/**
 * <p>
 * Simple integer vector that will be used throughout the entire TinyDungeon
 * library.
 * </p>
 */
public record Vector2(int x, int y) {
	public Vector2() {
		this(0, 0);
	}

	public Vector2 add(int x, int y) {
		return new Vector2(this.x + x, this.y + y);
	}

	public Vector2 add(Vector2 pos) {
		return new Vector2(x + pos.x, y + pos.y);
	}

	public Vector2 sub(int x, int y) {
		return new Vector2(this.x - x, this.y - y);
	}

	public Vector2 sub(Vector2 pos) {
		return new Vector2(x - pos.x, y - pos.y);
	}
}

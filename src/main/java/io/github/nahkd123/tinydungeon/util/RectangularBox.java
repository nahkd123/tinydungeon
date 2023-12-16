package io.github.nahkd123.tinydungeon.util;

/**
 * <p>
 * A rectangular box.
 * </p>
 */
public record RectangularBox(Vector2 origin, Vector2 size) implements Box {
	public RectangularBox(Box another) {
		this(another.getPosition(), another.getSize());
	}

	public RectangularBox(int x, int y, int width, int height) {
		this(new Vector2(x, y), new Vector2(width, height));
	}

	public RectangularBox() {
		this(new Vector2(), new Vector2());
	}

	@Override
	public Vector2 getPosition() { return origin; }

	@Override
	public Vector2 getSize() { return size; }

	/**
	 * <p>
	 * Increase the size of this rectangular box by attempting to fit another box
	 * into it, which expands the box.
	 * </p>
	 * 
	 * @param another Another box.
	 * @return Expanded box.
	 */
	public RectangularBox expand(Box another) {
		int nextX = Math.min(origin.x(), another.getPosition().x());
		int nextY = Math.min(origin.y(), another.getPosition().y());
		int nextX2 = Math.max(origin.x() + size.x(), another.getPosition().x() + another.getSize().y());
		int nextY2 = Math.max(origin.y() + size.y(), another.getPosition().y() + another.getSize().y());

		Vector2 nextPosition = new Vector2(nextX, nextY);
		Vector2 nextSize = new Vector2(nextX2 - nextX, nextY2 - nextY);
		return new RectangularBox(nextPosition, nextSize);
	}

	/**
	 * <p>
	 * Increase (or decrease) the size of this rectangular box by expanding the box
	 * to 4 directions by a specified units.
	 * </p>
	 * 
	 * @param amount The number of units to expand.
	 * @return Expanded box.
	 */
	public RectangularBox expand(int amount) {
		return new RectangularBox(origin.sub(amount, amount), size.add(amount * 2, amount * 2));
	}
}

package io.github.nahkd123.tinydungeon.util;

/**
 * <p>
 * An interface for anything that can be considered as "box".
 * </p>
 */
public interface Box {
	public Vector2 getPosition();

	public Vector2 getSize();

	default boolean isIntersectWith(int x, int y) {
		Vector2 min = getPosition();
		Vector2 max = getPosition().add(getSize());
		return x >= min.x() && x < max.x() && y >= min.y() && y < max.y();
	}

	default boolean isIntersectWith(Box another) {
		Vector2 aMin = getPosition();
		Vector2 aMax = getPosition().add(getSize());
		Vector2 bMin = another.getPosition();
		Vector2 bMax = another.getPosition().add(another.getSize());
		return aMin.x() < bMax.x() &&
			aMax.x() > bMin.x() &&
			aMin.y() < bMax.y() &&
			aMax.y() > bMin.y();
	}

	/**
	 * <p>
	 * Convert this box into {@link RectangularBox}, which contains more methods for
	 * transforming the box's geometry.
	 * </p>
	 * 
	 * @return The {@link RectangularBox} with the same geometry as this box.
	 */
	default RectangularBox asRectangularBox() {
		return new RectangularBox(this);
	}
}

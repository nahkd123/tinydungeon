package io.github.nahkd123.tinydungeon.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BoxTest {
	@Test
	void testIntersectBoxBox_notTouching() {
		RectangularBox a = new RectangularBox(0, 0, 3, 3);
		RectangularBox b = new RectangularBox(5, 5, 3, 3);
		assertFalse(a.isIntersectWith(b));
	}

	@Test
	void testIntersectBox_touchingOverlap() {
		RectangularBox a = new RectangularBox(0, 0, 3, 3);
		RectangularBox b = new RectangularBox(2, 0, 3, 3);
		assertTrue(a.isIntersectWith(b));
	}

	@Test
	void testIntersectBox_touchingPositiveX() {
		RectangularBox a = new RectangularBox(0, 0, 3, 3);
		RectangularBox b = new RectangularBox(3, 0, 3, 3);
		assertFalse(a.isIntersectWith(b));
	}

	@Test
	void testIntersectBox_touchingNegativeX() {
		RectangularBox a = new RectangularBox(0, 0, 3, 3);
		RectangularBox b = new RectangularBox(-3, 0, 3, 3);
		assertFalse(a.isIntersectWith(b));
	}
}

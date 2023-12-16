package io.github.nahkd123.tinydungeon.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RectangularBoxTest {
	@Test
	void testExpand() {
		RectangularBox box = new RectangularBox();
		RectangularBox a = new RectangularBox(-1, -1, 3, 3);
		RectangularBox b = new RectangularBox(-2, -2, 5, 5);
		RectangularBox c = new RectangularBox(-5, -1, 3, 3);
		box = box.expand(a).expand(b).expand(c);
		assertEquals(box, new RectangularBox(-5, -2, 8, 5));
	}
}

package io.github.nahkd123.tinydungeon.generate;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * <p>
 * A simple implementation of {@link GenerationContext}. You may use this
 * implementation if you are testing or you don't want to make your own
 * implementation.
 * </p>
 */
public class SimpleGenerationContext implements GenerationContext {
	private long seed;
	private Random random;

	public SimpleGenerationContext(long seed) {
		this.seed = seed;
		this.random = new Random(seed);
	}

	@Override
	public long getSeed() { return seed; }

	@Override
	public RandomGenerator getRandom() { return random; }
}

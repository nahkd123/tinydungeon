package io.github.nahkd123.tinydungeon.generate;

import java.util.random.RandomGenerator;

/**
 * <p>
 * The context interface for the generators.
 * </p>
 * <p>
 * Some people may implement this interface to their "dungeon session" object,
 * but if you don't want to implement this, you can use
 * {@link SimpleGenerationContext}.
 * </p>
 */
public interface GenerationContext {
	/**
	 * <p>
	 * Get the initial seed. This should not be changed for the entire generation
	 * process.
	 * </p>
	 * 
	 * @return The initial seed value.
	 */
	public long getSeed();

	/**
	 * <p>
	 * Get the sequential random number generator. This generator is based on the
	 * seed from {@link #getSeed()}.
	 * </p>
	 * <p>
	 * The next value is influenced by the initial seed value from
	 * {@link #getSeed()} and the previous value from the random number generator.
	 * </p>
	 * 
	 * @return Random number generator.
	 */
	public RandomGenerator getRandom();
}

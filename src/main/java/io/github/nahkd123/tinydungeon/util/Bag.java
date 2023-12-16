package io.github.nahkd123.tinydungeon.util;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

/**
 * <p>
 * Bag picks a random element without picking the same element within the number
 * of rolls that is less than the bag size.
 * </p>
 * <p>
 * Think of it as tetrominoes bag, except with elements.
 * </p>
 */
public class Bag<T> {
	private List<T> elements;
	private List<T> source;
	private boolean infinite;

	/**
	 * <p>
	 * Create a new randomly sorted bag.
	 * </p>
	 * 
	 * @param infinite Should this bag returns infinite amount of elements?
	 * @param source   Source of elements.
	 */
	public Bag(boolean infinite, List<T> source) {
		this.infinite = infinite;
		this.source = new ArrayList<>();
		this.source.addAll(source);
	}

	/**
	 * <p>
	 * Create a new infinite, randomly sorted bag.
	 * </p>
	 * 
	 * @param source Source of elements.
	 */
	public Bag(List<T> source) {
		this(true, source);
	}

	public boolean isInfinite() { return infinite; }

	/**
	 * <p>
	 * Pick a next element.
	 * </p>
	 * 
	 * @param random A random number source.
	 * @return Next element.
	 */
	public T next(RandomGenerator random) {
		if (source.size() == 0) return null;

		if (elements == null) {
			elements = new ArrayList<>();
			elements.addAll(source);
		}

		if (elements.size() == 0) {
			if (!infinite) return null;
			elements.addAll(source);
		}

		return elements.remove(random.nextInt(elements.size()));
	}
}

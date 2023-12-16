package io.github.nahkd123.tinydungeon.generate;

import java.util.List;

import io.github.nahkd123.tinydungeon.map.MutableDungeonMap;
import io.github.nahkd123.tinydungeon.room.RoomFactory;

/**
 * <p>
 * To procedurally generate a dungeon, you need a dungeon generator. Generators
 * takes in {@link GenerationContext} (which contains only random number
 * generator and its seed for now), a {@link MutableDungeonMap} (it has to be
 * mutable because the generator will add rooms to it) and a bunch of
 * {@link RoomFactory}.
 * </p>
 * <p>
 * Generator's settings are usually located within its instance. For example,
 * the branching depths settings for {@link JigsawDungeonGenerator} are
 * {@link JigsawDungeonGenerator#mainBranchDepth} and
 * {@link JigsawDungeonGenerator#auxBranchMaxDepth}.
 * </p>
 * <p>
 * Please note that the generator <i>might</i> fails to generate your new
 * dungeon. In this case,
 * {@link #generate(GenerationContext, MutableDungeonMap, List)} will returns
 * {@code false}. You can either restart the dungeon generation
 * ({@link GenerationContext} can be retained for next attempt) or stop the
 * generation now and figure out why it is not working. For
 * {@link JigsawDungeonGenerator}, the higher the depths, the higher the chance
 * of it not working.
 * </p>
 * <p>
 * TinyDungeon contains a single algorithm for generating dungeon: The
 * {@link JigsawDungeonGenerator}. It is called "jigsaw" because it builds the
 * dungeon like placing jigsaw pieces into the board/table/whatever that is.
 * </p>
 */
public interface DungeonGenerator {
	/**
	 * <p>
	 * Attempt to generate the dungeon, based on the current settings.
	 * </p>
	 * <p>
	 * Generator settings are configured by using setters or setting fields' value
	 * in the generator instance.
	 * </p>
	 * 
	 * @param context       The generation context.
	 * @param map           The mutable dungeon map.
	 * @param roomFactories A list of room factories that will be used to generate
	 *                      rooms. The generator may choose to pregenerate all rooms
	 *                      before putting some of them into the map, which means
	 *                      rooms that are generated doesn't mean they will be
	 *                      present in the map.
	 * @return true if this generator managed to generate the dungeon, false
	 *         otherwise.
	 */
	public boolean generate(GenerationContext context, MutableDungeonMap map, List<RoomFactory> roomFactories);
}

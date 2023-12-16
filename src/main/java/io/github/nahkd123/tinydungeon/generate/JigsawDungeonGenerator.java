package io.github.nahkd123.tinydungeon.generate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

import io.github.nahkd123.tinydungeon.feature.DoorFeature;
import io.github.nahkd123.tinydungeon.map.MutableDungeonMap;
import io.github.nahkd123.tinydungeon.room.Room;
import io.github.nahkd123.tinydungeon.room.RoomFactory;
import io.github.nahkd123.tinydungeon.util.Bag;
import io.github.nahkd123.tinydungeon.util.Vector2;

public class JigsawDungeonGenerator implements DungeonGenerator {
	/**
	 * <p>
	 * Number of rooms to pregenerate so that this algorithm can generate dungeons.
	 * Jigsaw algorithm works by generating a number of rooms, then find random room
	 * that is suitable and then put it into the map.
	 * </p>
	 */
	public int pregenerateBuffer = 5;

	/**
	 * <p>
	 * Auxiliary branches are branches that aren't important. These branches don't
	 * have to be generated to get a complete dungeon.
	 * </p>
	 */
	public int auxBranches = 5;

	/**
	 * <p>
	 * The exact depth for main branch.
	 * </p>
	 */
	public int mainBranchDepth = 10;

	/**
	 * <p>
	 * The maximum depth for auxiliary branches.
	 * </p>
	 */
	public int auxBranchMaxDepth = 5;

	/**
	 * <p>
	 * The factory for entrance. If the value is {@code null}, it will picks a
	 * random room in room factories bag as entrance and place at 0:0.
	 * </p>
	 * <p>
	 * Please note that the entrance will always be placed at 0:0, regardless of
	 * generation algorithm.
	 * </p>
	 */
	public RoomFactory entranceRoom = null;

	/**
	 * <p>
	 * The factory for the last room in the main branch. If the value is
	 * {@code null}, it will picks a random room in room factories bag as last room
	 * and place at main branch's tail.
	 * </p>
	 */
	public RoomFactory endRoom = null;

	@Override
	public boolean generate(GenerationContext context, MutableDungeonMap map, List<RoomFactory> roomFactories) {
		Bag<RoomFactory> factoriesBag = new Bag<>(roomFactories);
		RandomGenerator random = context.getRandom();
		RoomFactory entranceFactory = this.entranceRoom != null ? this.entranceRoom : factoriesBag.next(random);
		RoomFactory endFactory = this.endRoom != null ? this.endRoom : factoriesBag.next(random);
		List<Room> buffer = new ArrayList<>();

		Room entrance = entranceFactory.createNew(context);
		entrance.setPosition(0, 0);
		map.addRoom(entrance);

		// Generate main branch
		List<Room> mainBranch = generateBranch(context, map, entrance, mainBranchDepth, buffer, factoriesBag);
		if (mainBranch == null) return false;

		Room mainBranchTail = mainBranch.get(mainBranch.size() - 1);
		List<Room> tailSubbranch = generateBranch(context, map, mainBranchTail, 1, new ArrayList<>(),
			new Bag<>(Arrays.asList(endFactory)));
		if (tailSubbranch == null) return false;

		// Generate auxiliary branches
		List<Room> unbranchedRooms = new ArrayList<>();
		unbranchedRooms.addAll(mainBranch);

		for (int i = 0; i < auxBranches; i++) {
			Room selected = unbranchedRooms.remove(random.nextInt(unbranchedRooms.size()));
			generateBranch(context, map, selected, auxBranchMaxDepth, buffer, factoriesBag);
		}

		return true;
	}

	private List<Room> generateBranch(GenerationContext context, MutableDungeonMap map, Room current, int depth, List<Room> buffer, Bag<RoomFactory> factories) {
		RandomGenerator random = context.getRandom();
		fillBuffer(context, buffer, factories);

		// Pick random door
		Bag<DoorFeature> doorsBag = new Bag<>(false, current.getFeatures().stream()
			.map(v -> v instanceof DoorFeature d ? d : null)
			.filter(v -> v != null)
			.toList());
		DoorFeature door;

		while ((door = doorsBag.next(random)) != null) {
			DoorFeature currentDoor = door;

			// Pick random room
			Bag<Room> bufferBag = new Bag<>(false, buffer);
			Room next;

			while ((next = bufferBag.next(random)) != null) {
				List<DoorFeature> suitableNextDoors = next.getFeatures().stream()
					.map(v -> v instanceof DoorFeature d ? d : null)
					.filter(v -> v != null && v.getFacing() == currentDoor.getFacing().getOpposite())
					.toList();

				for (DoorFeature nextDoor : suitableNextDoors) {
					// Where the exit door is located
					Vector2 doorAbsPos = current.getPosition()
						.add(door.getPosition())
						.add(door.getFacing().getVector());
					Vector2 nextRoomPos = doorAbsPos.sub(nextDoor.getPosition());

					next.setPosition(nextRoomPos);
					Optional<Room> collision = map.findCollision(next);

					if (collision.isEmpty()) {
						// We've found next room!
						buffer.remove(next);
						map.addRoom(next);
						map.addConnection(current, currentDoor, next, nextDoor);

						if (depth <= 1) return Arrays.asList(next);
						List<Room> subbranch = generateBranch(context, map, next, depth - 1, buffer, factories);
						if (subbranch == null) return null;

						List<Room> branch = new ArrayList<>();
						branch.add(next);
						branch.addAll(subbranch);
						return branch;
					}
				}
			}
		}

		return null;
	}

	private void fillBuffer(GenerationContext context, List<Room> buffer, Bag<RoomFactory> factories) {
		while (buffer.size() < pregenerateBuffer) buffer.add(factories.next(context.getRandom()).createNew(context));
	}
}

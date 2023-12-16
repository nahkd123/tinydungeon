package io.github.nahkd123.tinydungeon.generate;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import io.github.nahkd123.tinydungeon.map.MutableDungeonMap;
import io.github.nahkd123.tinydungeon.map.SimpleMutableDungeonMap;
import io.github.nahkd123.tinydungeon.testimpl.FourWayRoom;

class JigsawDungeonGeneratorTest {
	Logger logger = Logger.getLogger("JigsawDungeonGeneratorTest");

	@Test
	void test() {
		JigsawDungeonGenerator generator = new JigsawDungeonGenerator();
		MutableDungeonMap map = new SimpleMutableDungeonMap();
		GenerationContext context = new SimpleGenerationContext(69_1337_727L);

		int attempt = 0;
		while (!generator.generate(context, map, Arrays.asList(FourWayRoom.FACTORY))) {
			logger.info("Generate failed! (Attempt %s)".formatted(++attempt));
			if (attempt >= 1000) fail("It has been 1000 attempts and no dungeon has been generated successfully.");
			map = new SimpleMutableDungeonMap();
		}

		map.printDebugInfo(System.out::println);
	}
}

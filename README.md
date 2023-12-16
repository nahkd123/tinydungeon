# nahkd's TinyDungeon
_A small library for procedural dungeon generation. Similar to Hypixel Skyblock dungeon, but can be used outside Minecraft!_

## Using TinyDungeon
TinyDungeon Maven artifact can be obtained from JitPack:

```groovy
// build.gradle
repositories {
    maven { url = 'https://jitpack.io/' }
}

dependencies {
    implementation 'io.github.nahkd123:tinydungeon:main-SNAPSHOT'
}
```

## Example
See [JigsawDungeonGeneratorTest.java](./src/test/java/io/github/nahkd123/tinydungeon/generate/JigsawDungeonGeneratorTest.java).

## Using TinyDungeon in your project
There are 2 ways to use TinyDungeon:

### The "proxy" approach
If you already have a room implementation and wanted to use Jigsaw generator, you can make your own "proxy", which basically proxies your room's position, size and dungeon doors (as features) from your implementation to your adapter.

The generator requires `RoomFactory`, which generates a new room with random features, but we can go with a simple factory that picks a random room in your rooms list for now:

```java
List<MyRoom> myRooms;

RoomFactory myFactory = context -> new MyRoomProxy(myRooms.get(context.nextInt(myRooms.size())));
```

> Note: `RoomFactory` might be changed in the future to include more abstract methods, so the above method might
not work if you are from the future.

### The "I'm gonna implement all of this" approach
If you don't have a room implementation, you can implement `Room` interface (and maybe `RoomFactory`). Note that the size of the room **should not** be changed during generation phase.

If you want a dynamic room size during playing phase, consider using the largest size for that room.

## Copyright and license
(c) nahkd 2023. Licensed under MIT license.

TODO add license header to all code.
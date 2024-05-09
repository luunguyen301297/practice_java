package org.example.monster_hunter.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameConfig {

    int numberOfMonsters;
    int bulletCount;
    int mapSize;
    boolean hideMonsterLocation;

    private GameConfig(final int numberOfMonsters, final int bulletCount, final int mapSize, final boolean hideMonsterLocation) {
        this.numberOfMonsters = numberOfMonsters;
        this.bulletCount = bulletCount;
        this.mapSize = mapSize;
        this.hideMonsterLocation = hideMonsterLocation;
    }

    public static GameConfig defaultConfig() {
        return builder().bulletCount(4).numberOfMonsters(4).mapSize(5).hideMonsterLocation(false).build();
    }

    public static GameConfigBuilder builder() {
        return new GameConfigBuilder();
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static final class GameConfigBuilder {

        int numberOfMonsters;
        int bulletCount;
        int mapSize;
        boolean hideMonsterLocation;

        public GameConfigBuilder numberOfMonsters(int numberOfMonsters) {
            this.numberOfMonsters = numberOfMonsters;
            return this;
        }

        public GameConfigBuilder bulletCount(int bulletCount) {
            this.bulletCount = bulletCount;
            return this;
        }

        public GameConfigBuilder mapSize(int mapSize) {
            this.mapSize = mapSize;
            return this;
        }

        public GameConfigBuilder hideMonsterLocation(boolean hideMonsterLocation) {
            this.hideMonsterLocation = hideMonsterLocation;
            return this;
        }

        public GameConfig build() {
            return new GameConfig(numberOfMonsters, bulletCount, mapSize, hideMonsterLocation);
        }

    }

}

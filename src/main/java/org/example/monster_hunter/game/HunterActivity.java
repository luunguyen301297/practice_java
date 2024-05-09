package org.example.monster_hunter.game;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.monster_hunter.model.Hunter;
import org.example.monster_hunter.model.Location;
import org.example.monster_hunter.model.Monster;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HunterActivity {

    Hunter hero;
    int boundary;
    List<Monster> monsters;
    HunterActivityWatcher watcher;

    public void moveUp() {
        System.out.println("Moving up");
        hero.moveUp(boundary);
        watcher.onComplete();
    }

    public void moveDown() {
        System.out.println("Moving down");
        hero.moveDown(boundary);
        watcher.onComplete();
    }

    public void moveLeft() {
        System.out.println("Moving left");
        hero.moveLeft(boundary);
        watcher.onComplete();
    }

    public void moveRight() {
        System.out.println("Moving right");
        hero.moveRight(boundary);
        watcher.onComplete();
    }

    public void shootUp() {
        System.out.println("Shoot up");
        final var heroLocation = hero.getLocation();
        final var shootLocation = heroLocation.transform(0, - 1);
        shoot(shootLocation);
    }

    public void shootDown() {
        System.out.println("Shoot down");
        final var heroLocation = hero.getLocation();
        final var shootLocation = heroLocation.transform(0, 1);
        shoot(shootLocation);
    }

    public void shootLeft() {
        System.out.println("Shoot left");
        final var heroLocation = hero.getLocation();
        final var shootLocation = heroLocation.transform(- 1, 0);
        shoot(shootLocation);
    }

    public void shootRight() {
        System.out.println("Shoot right");
        final var heroLocation = hero.getLocation();
        final var shootLocation = heroLocation.transform(1, 0);
        shoot(shootLocation);
    }

    private void shoot(Location shootLocation) {
        final var monster = monsters.stream()
                .filter(m -> m.getLocation().equals(shootLocation))
                .findAny()
                .orElse(null);
        hero.shoot(monster);
        if (monster != null && ! monster.alive()) {
            System.out.println("Good shoot");
            monsters.remove(monster);
        }
        watcher.onComplete();
    }

}

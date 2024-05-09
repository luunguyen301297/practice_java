package org.example.monster_hunter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class Location {

    private int x;
    private int y;

    public static Location clone(Location location) {
        return new Location(location.getX(), location.getY());
    }

    public Location transform(int x, int y) {
        return new Location(this.x + x, this.y + y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean equals(Object point) {
        if (point == null) {
            return false;
        }
        if (point instanceof Location) {
            return (x == ((Location) point).x) && (y == ((Location) point).y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    public <T extends Location> boolean nearAnyLocation(Collection<T> locations) {
        return locations.stream().anyMatch(l -> {
            if (l.getX() == this.getX()) {
                if (l.getY() == this.getY() - 1 || l.getY() == this.getY() + 1) {
                    return true;
                }
            }
            if (l.getY() == this.getY()) {
                if (l.getX() == this.getX() - 1 || l.getX() == this.getX() + 1) {
                    return true;
                }
            }
            if (l.getX() == this.getX() + 1 || l.getX() == this.getX() - 1) {
                return l.getY() == this.getY() + 1 || l.getY() == this.getY() - 1;
            }
            return false;
        });
    }

}

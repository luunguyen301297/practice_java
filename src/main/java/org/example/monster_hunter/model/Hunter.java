package org.example.monster_hunter.model;

import org.example.monster_hunter.action.Attackable;
import org.example.monster_hunter.action.Movable;

public abstract class Hunter extends Character implements Movable, Attackable {

    protected Hunter(final Location location) {
        super(location);
    }

    public abstract boolean outOfBullet();

}

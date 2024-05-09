package org.example.monster_hunter.model;

import org.example.monster_hunter.action.Damaged;

public abstract class Monster extends Character implements Damaged {

    protected Monster(final Location location) {
        super(location);
    }

}
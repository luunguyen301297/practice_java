package org.example.monster_hunter.action;

import org.example.monster_hunter.model.Hunter;

public interface Damaged {
    void beAttacked(int hp);

    void attackedBy(Hunter hunter);
}

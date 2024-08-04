package org.example.design_pattern.strategy;

import java.util.ArrayList;
import java.util.List;

public final class SortedList {

    private SortStrategy strategy;
    private final List<Integer> items = new ArrayList<>();

    public void setSortStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void add(Integer item) {
        items.add(item);
    }

    public List<Integer> sort() {
        strategy.sort(items);
        return items;
    }

}

package com.mobiquity.vo;

import java.util.List;

public final class LineItem {
    List<Item> items;
    int maxWeight=0;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}

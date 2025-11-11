package org.skypro.skyshop.model.basket;

import java.util.ArrayList;
import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int totalSum;

    public UserBasket(List<BasketItem> items) {
        this.items = List.copyOf(items);
        this.totalSum = items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotalSum() {
        return totalSum;
    }
}

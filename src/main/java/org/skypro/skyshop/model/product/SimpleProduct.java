package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;
    private final UUID id;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price < 1) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.price = price;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getPrice() {

        return price;
    }

    @Override
    public boolean isSpecial() {

        return false;
    }

    @Override
    public String toString() {
        return "имя продукта: " + getName() + " стоимость " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimpleProduct that = (SimpleProduct) o;
        return price == that.price;
    }
}

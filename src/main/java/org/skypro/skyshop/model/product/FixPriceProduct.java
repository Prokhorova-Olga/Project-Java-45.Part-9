package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 100;
    private final UUID id;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {

        return "имя продукта c фиксированной ценой: " + getName() + " Фиксированная цена " + FIXED_PRICE;
    }
}

package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercentage;
    private final UUID id;


    public DiscountedProduct(String name, int basePrice, int discountPercent, UUID id) {
        super(name, id);
        if (basePrice < 1) {
            throw new IllegalArgumentException("Базовая цена должна быть больше нуля");
        } else if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки не может быть меньше 0 или больше 100");
        }
        this.basePrice = basePrice;
        this.discountPercentage = discountPercent;
        this.id = id;


    }

    @Override
    public int getPrice() {
        return basePrice - ((basePrice * discountPercentage) / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "имя продукта со скидкой: " + getName() + " стоимость: " + getPrice() + " скидка " + discountPercentage + "%)";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return basePrice == that.basePrice && discountPercentage == that.discountPercentage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePrice, discountPercentage);
    }

}

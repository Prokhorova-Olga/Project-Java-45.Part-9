package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public abstract int getPrice();

    public abstract String toString();

    public abstract boolean isSpecial();

@JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName() + " PRODUCT";
    }
@JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getSearchableName() {
        return getName();
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}

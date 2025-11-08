package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    String getSearchableName();

    default void getStringRepresentation() {
        System.out.println("Имя " + getSearchableName() + " - тип " + getContentType());
    }

    UUID getId();
}

package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;


    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Название статьи: " + title + "\n " +
                "Текст статьи: " + text;
    }
@JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString() + " ARTICLE";
    }
@JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getSearchableName() {
        return title;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}

package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;


    public StorageService() {
        this.articles = new HashMap<>();
        this.products = new HashMap<>();
        this.initStorage();
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    private void initStorage() {
        Product apple = new SimpleProduct("яблоко", 100, UUID.randomUUID());
        Product strasberry = new SimpleProduct("клубника", 600, UUID.randomUUID());
        Product watermelon = new SimpleProduct("арбуз", 50, UUID.randomUUID());
        Product orange = new DiscountedProduct("апельсин", 150, 10, UUID.randomUUID());
        Product lemon = new FixPriceProduct("лимон", UUID.randomUUID());
        Article cherry = new Article("Польза вишни", "Bишня - очень полезна", UUID.randomUUID());
        Article mango = new Article("Вкус манго", "Mанго или вишня?", UUID.randomUUID());
        products.put(apple.getId(), apple);
        products.put(strasberry.getId(), strasberry);
        products.put(watermelon.getId(), watermelon);
        products.put(orange.getId(), orange);
        products.put(lemon.getId(), lemon);
        articles.put(cherry.getId(), cherry);
        articles.put(mango.getId(), mango);
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> allSearchable = new ArrayList<>();
        allSearchable.addAll(articles.values());
        allSearchable.addAll(products.values());
        return allSearchable;
    }

}

package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductById(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalStateException("Продукт с таким id " + id + " не найден");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketProducts = productBasket.getProducts();
        List<BasketItem> basketItems = basketProducts.entrySet().stream().map(entry -> {
            UUID productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = storageService.getProductById(productId).orElseThrow(() -> new IllegalStateException("Продукт с таким id " + productId + " не найден"));
            return new BasketItem(product, quantity);
        }).collect(Collectors.toList());
        return new UserBasket(basketItems);

    }



}

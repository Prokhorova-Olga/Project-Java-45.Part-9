package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void givenNotExistentProductId_whenAddProductById_thenThrowIllegalStateException() {

        UUID nonExistentId = randomUUID();

        when(storageService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            basketService.addProductById(nonExistentId);
        });
        assertTrue(exception.getMessage().contains("не найден"));
        assertTrue(exception.getMessage().contains(nonExistentId.toString()));
    }

    @Test
    public void givenExistentProductId_whenAddProductById_thenCallAddProductOnBasket() {

        UUID existentID = UUID.randomUUID();
        Product existingtProduct = new SimpleProduct("яблоко", 100, existentID);

        when(storageService.getProductById(existentID)).thenReturn(Optional.of(existingtProduct));
        basketService.addProductById(existentID);

        verify(productBasket).addProduct(existentID);
    }

    @Test
    public void givenEmptyProductBasket_whenGetUserBasket_thenReturnEmptyBasket() {

        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty());
    }

    @Test
    public void givenProductBasketWithItems_whenGetUserBasket_thenReturnBasketWithItems() {

        UUID productIDOne = UUID.randomUUID();
        UUID productIDTwo = UUID.randomUUID();
        Product productOne = new SimpleProduct("кокос", 300, productIDOne);
        Product productTwo = new SimpleProduct("клубника", 1300, productIDTwo);
        Map<UUID, Integer> basketProducts = Map.of(
                productIDOne, 1,
                productIDTwo, 2
        );
        when(productBasket.getProducts()).thenReturn(basketProducts);
        when(storageService.getProductById(productIDOne)).thenReturn(Optional.of(productOne));
        when(storageService.getProductById(productIDTwo)).thenReturn(Optional.of(productTwo));

        UserBasket result = basketService.getUserBasket();

        assertFalse(result.getItems().isEmpty());
        assertEquals(2, result.getItems().size());

        for (BasketItem item : result.getItems()) {
            if (item.getProduct().getId().equals(productIDOne)) {
                assertEquals(1, item.getQuantity());
            } else if (item.getProduct().getId().equals(productIDTwo)) {
                assertEquals(2, item.getQuantity());
            }
        }
    }
}

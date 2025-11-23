package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyStorage_whenSearch_thenReturnEmptyList() {


        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("яблоко");

        assertTrue(results.isEmpty());

    }

    @Test
    public void givenStorageWithProductsButNoMatch_whenSearch_thenReturnEmptyList() {

        when(storageService.getAllSearchable()).thenReturn(List.of(
                new SimpleProduct("банан", 100, UUID.randomUUID()),
                new SimpleProduct("апельсин", 90, UUID.randomUUID()),
                new SimpleProduct("морковь", 150, UUID.randomUUID())
        ));

        Collection<SearchResult> results = searchService.search("яблоко");

        assertTrue(results.isEmpty());

    }

    @Test
    public void givenStorageWithMatchingProduct_whenSearch_thenReturnProduct() {

        when(storageService.getAllSearchable()).thenReturn(List.of(
                new SimpleProduct("яблоко", 110, UUID.randomUUID())
        ));

        Collection<SearchResult> results = searchService.search("яблоко");

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("яблоко", results.iterator().next().getName());


    }


}

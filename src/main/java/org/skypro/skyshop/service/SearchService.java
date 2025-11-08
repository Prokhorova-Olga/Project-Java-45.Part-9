package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;


    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String query) {
        Collection<Searchable> allSearchables = storageService.getAllSearchable();
        return allSearchables.stream()
                .map(SearchResult::fromSearchable)
                .filter(searchResult ->
                        searchResult.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

}

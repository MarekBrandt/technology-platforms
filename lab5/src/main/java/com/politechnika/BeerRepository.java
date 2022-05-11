package com.politechnika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class BeerRepository {
    private Collection<Beer> collection;
    public BeerRepository() {
        collection = new ArrayList<>();
    }

    public Optional<Beer> find(String name) {
        for (Beer beer : collection) {
            if ( beer.getNazwa().equals(name)) {
                return Optional.of(beer);
            }
        }
        return Optional.empty();
    }
    public void delete(String name) throws IllegalArgumentException {
        Optional<Beer> optMage = find(name);

        if (optMage.isEmpty()) {
            throw new IllegalArgumentException("Beer not found");
        }
        collection.remove(optMage.get());

    }
    public void save(Beer beer) {
        Optional<Beer> optMage = find(beer.getNazwa());

        if (optMage.isPresent()) {
            throw new IllegalArgumentException("Already in collection");
        }
        collection.add(beer);
    }

}


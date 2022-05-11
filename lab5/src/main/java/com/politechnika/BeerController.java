package com.politechnika;

import java.util.Optional;

public class BeerController {
    private BeerRepository repository;
    public BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Beer> optMage = repository.find(name);
        if (optMage.isEmpty()) {
            return "not found";
        }
        return optMage.get()
                .toString();
    }

    public String delete(String name) {
        String odp = "done";
        try {
            repository.delete(name);

        } catch (IllegalArgumentException e) {
            odp = "not found";
        }
        return odp;
    }
    public String save(String name, float procenty) {
        String odp = "done";
        try {
            Beer beer = new Beer(name, procenty);
            repository.save(beer);

        } catch (IllegalArgumentException e) {
            odp = "bad request";
        }
        return odp;
    }

}


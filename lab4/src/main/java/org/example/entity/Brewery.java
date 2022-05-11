package org.example.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
public class Brewery {
    @Id
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long value;

    @Getter
    @Setter
    @OneToMany(mappedBy = "brewery", fetch = FetchType.EAGER)
    private List<Beer> beers = new ArrayList<>();

    public Brewery(){};

    public Brewery(String name, long value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String toString() {
        return "Brewery{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
public class Beer {
    @Id
    @Getter
    private String name;

    @Getter
    @Setter
    private long price;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brewery")
    private Brewery brewery;

    public Beer() {}

    public Beer(String name, long price, Brewery brewery) {
        this.name = name;
        this.price = price;
        this.brewery = brewery;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brewery=" + brewery +
                '}';
    }
}
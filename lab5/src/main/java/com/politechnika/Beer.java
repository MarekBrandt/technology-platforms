package com.politechnika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @Setter
    @Getter
    private String nazwa;

    @Setter
    @Getter
    private float procenty;

    @Override
    public String toString() {
        return "Beer{" +
                "nazwa='" + nazwa + '\'' +
                ", procenty=" + procenty +
                '}';
    }
}


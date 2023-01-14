package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.IntegerValuePrototype;

public class RandomInteger {

    static int getRandomInt(int minRange, int maxRange) {
        return minRange + (int) Math.round(Math.random() * (maxRange - minRange));
    }

    static int getRandomInt(IntegerValuePrototype prototype) {
        return getRandomInt(prototype.getNumberBoundaries().getMin(), prototype.getNumberBoundaries().getMax());
    }

}

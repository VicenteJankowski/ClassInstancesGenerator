package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.prototype.IntegerValuePrototype;

public class RandomInteger {

    static int generate(int minRange, int maxRange) {
        return minRange + (int) Math.round(Math.random() * (maxRange - minRange));
    }

    static int generate(IntegerValuePrototype prototype) {
        return generate(prototype.getNumberBoundaries().getMin(), prototype.getNumberBoundaries().getMax());
    }

}

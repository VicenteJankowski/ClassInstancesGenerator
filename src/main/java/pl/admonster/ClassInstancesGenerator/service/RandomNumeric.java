package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.prototype.NumericValuePrototype;

public class RandomNumeric {

    static float generate(float minRange, float maxRange) {
        return minRange + Math.round(Math.random() * (maxRange - minRange));
    }

    static float generate(int minRange, int maxRange) {
        return generate(minRange, maxRange);
    }

    static float generate(NumericValuePrototype prototype) {
        return generate(prototype.getNumberBoundaries().getMin(), prototype.getNumberBoundaries().getMax());
    }

}

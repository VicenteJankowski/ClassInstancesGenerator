package pl.admonster.ClassInstancesGenerator.model;

import java.lang.reflect.Field;

public class IntegerValuePrototype extends ValuePrototype {

    private static enum DefaultRange {
        ;
        private static final int MIN = 0;
        private static final int MAX = 1000;
    }

    Range numberBoundaries = new Range(DefaultRange.MIN, DefaultRange.MAX);

    public IntegerValuePrototype(Field generationBase) {
        super(generationBase);
    }

    public Range getNumberBoundaries() {
        return numberBoundaries;
    }

    public void setNumberBoundaries(Range numberBoundaries) {
        this.numberBoundaries = numberBoundaries;
    }

}

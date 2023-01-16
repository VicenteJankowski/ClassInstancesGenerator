package pl.admonster.ClassInstancesGenerator.model.prototype;

import pl.admonster.ClassInstancesGenerator.model.Range;

import java.lang.reflect.Field;

public class IntegerValuePrototype extends ValuePrototype {

    private enum DefaultRange {
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

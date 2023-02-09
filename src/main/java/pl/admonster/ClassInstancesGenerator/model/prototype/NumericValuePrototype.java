package pl.admonster.ClassInstancesGenerator.model.prototype;

import pl.admonster.ClassInstancesGenerator.annotation.RangeOfGeneratedValue;
import pl.admonster.ClassInstancesGenerator.model.Range;

import java.lang.reflect.Field;

public class NumericValuePrototype extends ValuePrototype {

    private enum DefaultRange {
        ;
        private static final int MIN = 0;
        private static final int MAX = 100;
    }

    Range numberBoundaries;

    private NumericValuePrototype(final Field generationBase) {
        super(generationBase);
    }
    private NumericValuePrototype(Field generationBase, float minRange, float maxRange) {
        super(generationBase);
        setNumberBoundaries(new Range(minRange, maxRange));
    }

    public static NumericValuePrototype create(Field generationBase) {
        if(generationBase.isAnnotationPresent(RangeOfGeneratedValue.class))
            return new NumericValuePrototype(generationBase,
                                             generationBase.getAnnotation(RangeOfGeneratedValue.class).min(),
                                             generationBase.getAnnotation(RangeOfGeneratedValue.class).max());
        else
            return new NumericValuePrototype(generationBase, DefaultRange.MIN, DefaultRange.MAX);
    }

    public Range getNumberBoundaries() {
        return numberBoundaries;
    }

    public void setNumberBoundaries(Range numberBoundaries) {
        this.numberBoundaries = numberBoundaries;
    }

}

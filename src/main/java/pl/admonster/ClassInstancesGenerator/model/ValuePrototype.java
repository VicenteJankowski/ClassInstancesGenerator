package pl.admonster.ClassInstancesGenerator.model;

import java.lang.reflect.Field;

abstract class ValuePrototype{

    final Field generationBase;

    Range length;

    protected ValuePrototype(Field generationBase) {
        this.generationBase = generationBase;
    }

    public Field getGenerationBase() {
        return generationBase;
    }

    public Range getLength() {
        return length;
    }

    public void setLength(Range length) {
        this.length = length;
    }
}

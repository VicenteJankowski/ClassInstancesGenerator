package pl.admonster.ClassInstancesGenerator.model.prototype;

import pl.admonster.ClassInstancesGenerator.model.Range;

import java.lang.reflect.Field;

abstract class ValuePrototype{

    final Field generationBase;

    Range requestedLength;

    protected ValuePrototype(Field generationBase) {
        this.generationBase = generationBase;
    }

    public Field getGenerationBase() {
        return generationBase;
    }

    public Range getRequestedLength() {
        return requestedLength;
    }

    public void setRequestedLength(Range requestedLength) {
        this.requestedLength = requestedLength;
    }
}

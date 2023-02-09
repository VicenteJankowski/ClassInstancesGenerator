package pl.admonster.ClassInstancesGenerator.model.prototype;

import pl.admonster.ClassInstancesGenerator.model.Range;
import pl.admonster.ClassInstancesGenerator.service.RandomString.CharsProvider;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Set;

import static pl.admonster.ClassInstancesGenerator.service.RandomString.CharsProvider.*;

public class StringValuePrototype extends ValuePrototype {

    private Set<CharsProvider> desireChars = EnumSet.of(LatinLettersLowercase, LatinLettersUppercase, Digits);
    private enum DefaultLength {
        ;
        private static final int MIN = 8;
        private static final int MAX = 25;
    }

    public StringValuePrototype(final Field generationBase) {
        super(generationBase);
        this.requestedLength = new Range();
        this.requestedLength.setMin(DefaultLength.MIN);
        this.requestedLength.setMax(DefaultLength.MAX);
    }

    public StringValuePrototype(final Field generationBase, final int minLength, final int maxLength) {
        super(generationBase);
        this.requestedLength.setMin(minLength);
        this.requestedLength.setMax(maxLength);
    }

    public Set<CharsProvider> getDesireChars() {
        return desireChars;
    }

    public void setDesireChars(Set<CharsProvider> desireChars) {
        this.desireChars = desireChars;
    }
}

package pl.admonster.ClassInstancesGenerator.model;

import java.lang.reflect.Field;

public class StringValuePrototype extends ValuePrototype {

    public static enum CaseSensitivity {
        OnlyUpperCase, OnlyLowerCase, UpperAndLowerCase;
    }

    private CaseSensitivity caseSensitivityOption = CaseSensitivity.OnlyLowerCase;

    private static enum DefaultLength {
        ;
        private static final int MIN = 3;
        private static final int MAX = 8;
    }

    public StringValuePrototype(final Field generationBase) {
        super(generationBase);
        this.length = new Range();
        this.length.setMin(DefaultLength.MIN);
        this.length.setMax(DefaultLength.MAX);
    }

    public StringValuePrototype(final Field generationBase, final int minLength, final int maxLength) {
        super(generationBase);
        this.length.setMin(minLength);
        this.length.setMax(maxLength);
    }

    public CaseSensitivity getCaseSensitivityOption() {
        return caseSensitivityOption;
    }

    public void setCaseSensitivityOption(CaseSensitivity caseSensitivityOption) {
        this.caseSensitivityOption = caseSensitivityOption;
    }
}

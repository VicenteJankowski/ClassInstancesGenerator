package pl.admonster.ClassInstancesGenerator.model;

public class Range {

    private int minValue;
    private int maxValue;

    public Range() {}
    public Range(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMin() {
        return minValue;
    }

    public void setMin(int minValue) {
        this.minValue = minValue;
    }

    public int getMax() {
        return maxValue;
    }

    public void setMax(int maxValue) {
        this.maxValue = maxValue;
    }
}

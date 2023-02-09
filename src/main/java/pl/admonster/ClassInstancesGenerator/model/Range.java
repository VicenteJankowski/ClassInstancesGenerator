package pl.admonster.ClassInstancesGenerator.model;

public class Range {

    private float minValue;
    private float maxValue;

    public Range(float minValue, float maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public Range(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public float getMin() {
        return minValue;
    }

    public void setMin(int minValue) {
        this.minValue = minValue;
    }

    public float getMax() {
        return maxValue;
    }

    public void setMax(int maxValue) {
        this.maxValue = maxValue;
    }
}

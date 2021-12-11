package io.github.hooferdevelops.utility;

public class IncrementalNumber {
    public float currentValue = 0;

    public IncrementalNumber(float baseValue){
        this.currentValue = baseValue;
    }

    public float getCurrentValue(){
        return this.currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
    }

    public void incrementCurrentValue(float incrementValue){
        this.currentValue += incrementValue;
    }
}

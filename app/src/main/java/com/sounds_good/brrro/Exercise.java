package com.sounds_good.brrro;

/**
 * Created by pgoggijr on 4/22/15.
 */
public class Exercise {
    private int[] sets;
    private int type;
    private int reps;
    private int weight;

    public Exercise(int setCount, int repCount, int xWeight, int exerciseType) {
        if(setCount <= 0) {
            setCount = 1;
        }
        if (repCount <= 0) {
            repCount = 1;
        }
        sets = new int[setCount];
        type = exerciseType;
        reps = repCount;
        weight = xWeight;
    }

    public int[] getSets() {
        return sets;
    }
    public int getType() {
        return type;
    }
    public int getWeight() {
        return weight;
    }
    public boolean setWeight(int newWeight) {
        weight = newWeight;
        return true;
    }
    public void updateSet(int index) {
        if(sets[index] == 0) {
            sets[index] = reps;
        } else {
            sets[index] = sets[index] - 1;
        }
    }




}

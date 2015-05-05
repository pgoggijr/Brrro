package com.sounds_good.brrro;

/**
 * Created by pgoggijr on 4/22/15.
 * class to describe a single exercise
 */
public class Exercise {
    private int[] sets;
    private int type;
    private int reps;
    private int weight;

    /* Create exercise without a set array */
    public Exercise(int setCount, int repCount, int xWeight, int exerciseType) {
        if(setCount <= 0) {
            setCount = 1;
        }
        if (repCount <= 0) {
            repCount = 1;
        }
        if (xWeight <= 0) {
            xWeight = 10;
        }
        sets = new int[setCount];
        type = exerciseType;
        reps = repCount;
        weight = xWeight;
    }

    /* Create exercise with a set array */
    public Exercise(int setCount, int repCount, int xWeight, int exerciseType, int setArr[]) {
        boolean valid = true;

        if(setCount <= 0) {
            setCount = 1;
        }
        if (repCount <= 0) {
            repCount = 1;
        }
        /* verify set array is valid */
        if(setArr.length != setCount) {
            valid = false;
        }
        for(int i = 0; valid && i < setArr.length; i++) {
            if(setArr[i] < 0 || setArr[i] > repCount) {
                valid = false;
            }
        }
        /* use given sets if valid, default if invalid */
        if(valid) {
            sets = setArr;
        } else {
            sets = new int[repCount];
        }
        type = exerciseType;
        reps = repCount;
        weight = xWeight;
    }

    public int[] getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
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
    public int updateSet(int index) {
        if(index < 0 || index >= sets.length) {
            return 0;
        }
        if(sets[index] == 0) {
            sets[index] = reps;
        } else {
            sets[index] = sets[index] - 1;
        }
        return sets[index];
    }
    public boolean updateSet(int index, int value) {
        if(index < 0
                || index > sets.length
                || value < 0
                || value > reps) {
            return false;
        }
        sets[index] = value;
        return true;
    }

    public void printExercise() {
        System.out.println("Exercise Type: " + type +
                ", Exercise weight: " + weight + ", " +
                "Exercise max reps: " + reps);
        System.out.print("[");
        for(int i = 0; i < sets.length; i++) {
            System.out.print(sets[i]);
            if(i != sets.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}

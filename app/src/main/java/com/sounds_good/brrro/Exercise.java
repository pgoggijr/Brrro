package com.sounds_good.brrro;

import java.util.Arrays;

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
    public Exercise(int sets, int reps, int weight, int type) {
        if(sets <= 0) {
            sets = 1;
        }
        if (reps <= 0) {
            reps = 1;
        }
        if (weight <= 0) {
            weight = 10;
        }
        this.sets = new int[sets];
        this.type = type;
        this.reps = reps;
        this.weight = weight;
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

    public boolean setWeight(int weight) {
        if(weight <= 0) {
            return false;
        }
        this.weight = weight;
        return true;
    }
    public int updateSet(int index) {
        if(index < 0 || index >= sets.length) {
            return -1;
        }
        if(sets[index] == 0) {
            sets[index] = reps;
        } else {
            sets[index] = sets[index] - 1;
        }
        return sets[index];
    }
    public int updateSet(int index, int value) {
        if(index < 0 || index > sets.length) {
            return -1;
        }
        if(value >= 0 && value <= reps) {
            sets[index] = value;
        }
        return sets[index];
    }

    public boolean equalTo(Exercise exercise) {
            return this.type == exercise.getType()
                && this.weight == exercise.getWeight()
                && this.reps == exercise.getReps()
                && Arrays.equals(this.sets, exercise.getSets());
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

package com.sounds_good.brrro;

/*
 * Created by pgoggijr on 4/22/15.
 */
public interface Workout {
    Exercise[] getExercises();
    int getTotalSets();
    int getType();
    int getDate();
}

package com.sounds_good.brrro;

import java.util.Date;
/**
 * Created by pgoggijr on 4/22/15.
 */
public class Workout {
    public final static int WORKOUT_A = 0;
    public final static int WORKOUT_B = 1;

    private Exercise[] exercises;
    private int type;
    private Date date;

    public Workout(int type, Date date) {
        ExerciseFactory factory = new ExerciseFactory();

        this.date = date;
        if(type == WORKOUT_A){
            exercises = new Exercise[8];
            exercises[0] = factory.createExercise(
                    ExerciseFactory.SQUAT,45);
            exercises[1] = factory.createExercise(
                    ExerciseFactory.BENCH_PRESS,45);
            exercises[2] = factory.createExercise(
                    ExerciseFactory.BENT_OVER_ROW,45);
            exercises[3] = factory.createExercise(
                    ExerciseFactory.BARBELL_SHRUGS,45);
            exercises[4] = factory.createExercise(
                    ExerciseFactory.TRICEP_EXTENSIONS,45);
            exercises[5] = factory.createExercise(
                    ExerciseFactory.INCLINE_CURLS,45);
            exercises[6] = factory.createExercise(
                    ExerciseFactory.HYPEREXTENSIONS,45);
            exercises[7] = factory.createExercise(
                    ExerciseFactory.CABLE_CRUNCHES,45);
            this.type = WORKOUT_A;

        } else {
            exercises = new Exercise[7];
            exercises[0] = factory.createExercise(
                    ExerciseFactory.SQUAT,45);
            exercises[1] = factory.createExercise(
                    ExerciseFactory.DEADLIFT,45);
            exercises[2] = factory.createExercise(
                    ExerciseFactory.STANDING_PRESS,45);
            exercises[3] = factory.createExercise(
                    ExerciseFactory.BENT_OVER_ROW,45);
            exercises[4] = factory.createExercise(
                    ExerciseFactory.CLOSE_GRIP_BENCH_PRESS,45);
            exercises[5] = factory.createExercise(
                    ExerciseFactory.INCLINE_CURLS,45);
            exercises[6] = factory.createExercise(
                    ExerciseFactory.CABLE_CRUNCHES,45);
            this.type = WORKOUT_B;
        }
    }

    public Exercise[] getExercises() {
        return exercises;
    }
    public int getTotalSets() {
        int total = 0;
        for (Exercise exercise : exercises) {
            total = total + exercise.getSets().length;
        }
        return total;
    }
    public int getType() {
        return type;
    }
    public Date getDate() {
        return date;
    }

    public void printWorkout() {
        for(Exercise exercise : exercises) {
            exercise.printExercise();
        }
    }
}

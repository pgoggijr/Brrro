package com.sounds_good.brrro;

/**
 * Created by pgoggijr on 4/23/15.
 */
public class ExerciseFactory {
    /* constants */
    public static final int SQUAT = 1;
    public static final int OVERHEAD_PRESS = 2;
    public static final int BENCH_PRESS = 3;
    public static final int BENT_OVER_ROW = 4;
    public static final int BARBELL_SHRUGS = 5;
    public static final int TRICEP_EXTENSIONS = 6;
    public static final int INCLINE_CURLS = 7;
    public static final int HYPEREXTENSIONS = 8;
    public static final int CABLE_CRUNCHES = 9;
    public static final int DEADLIFT = 10;
    public static final int STANDING_PRESS = 12;
    public static final int CLOSE_GRIP_BENCH_PRESS = 13;

    public ExerciseFactory(){}

    public Exercise createExercise(int type, int weight) {
        switch (type) {
            case SQUAT:
                return new Exercise(5,weight);
            case OVERHEAD_PRESS:
                return new Exercise(5,weight);

        }
    }
}

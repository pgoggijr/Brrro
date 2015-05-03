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
    public static final int STANDING_PRESS = 11;
    public static final int CLOSE_GRIP_BENCH_PRESS = 12;

    public ExerciseFactory(){}

    public Exercise createExercise(int type, int weight) {
        switch (type) {
            case SQUAT:
            case OVERHEAD_PRESS:
            case BENCH_PRESS:
            case BENT_OVER_ROW:
            case STANDING_PRESS:
                return new Exercise(5, 5, weight, type);
            case BARBELL_SHRUGS:
            case TRICEP_EXTENSIONS:
            case INCLINE_CURLS:
            case CLOSE_GRIP_BENCH_PRESS:
                return new Exercise(3, 8, weight, type);
            case HYPEREXTENSIONS:
                return new Exercise(2, 10, weight, type);
            case DEADLIFT:
                return new Exercise(1, 5, weight, type);
            case CABLE_CRUNCHES:
                return new Exercise(3, 10, weight, type);
            default:
                return new Exercise(5, 5, weight, 1);
        }
    }

    public Exercise createExercise(int type, int weight, int[] sets) {
        switch (type) {
            case SQUAT:
            case OVERHEAD_PRESS:
            case BENCH_PRESS:
            case BENT_OVER_ROW:
            case STANDING_PRESS:
                return new Exercise(5, 5, weight, type, sets);
            case BARBELL_SHRUGS:
            case TRICEP_EXTENSIONS:
            case INCLINE_CURLS:
            case CLOSE_GRIP_BENCH_PRESS:
                return new Exercise(3, 8, weight, type, sets);
            case HYPEREXTENSIONS:
                return new Exercise(2, 10, weight, type, sets);
            case DEADLIFT:
                return new Exercise(1, 5, weight, type, sets);
            case CABLE_CRUNCHES:
                return new Exercise(3, 10, weight, type, sets);
            default:
                return new Exercise(0, 0, weight, type, sets);
        }
    }
}

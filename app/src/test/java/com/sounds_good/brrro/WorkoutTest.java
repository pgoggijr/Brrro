package com.sounds_good.brrro;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pgoggijr on 5/6/15.
 */
public class WorkoutTest extends TestCase {

    private Workout testWorkout;

    public WorkoutTest() {
        super("Workout Test");
    }

    @Before
    public void setUp() throws Exception {
        testWorkout = new Workout(Workout.WORKOUT_A,"20150506");
    }

    @Test
    public void testGetExercises() throws Exception {
        Exercise[] testExercises = testWorkout.getExercises();
        assertEquals("Should be an 8 long array of exercises",true,
                testExercises.getClass().equals(Exercise[].class)
                    && testExercises.length == 8);
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals("testWorkout.getType should be Workout.WORKOUT_A",
                Workout.WORKOUT_A,testWorkout.getType());
    }

    @Test
    public void testGetDate() throws Exception {
        assertEquals("testWorkout.getDate should be 2015006","20150506",
                testWorkout.getDate());
    }
}
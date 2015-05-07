package com.sounds_good.brrro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by Peter on 5/6/2015.
 */
public class ExerciseFactoryTest {

    private ExerciseFactory testFactory;
    private Exercise testExercise1;
    private Exercise testExercise2;
    private Exercise testExercise3;
    private Exercise testExercise4;
    private Exercise testExercise5;
    private Exercise testExercise6;
    private Exercise testExercise7;
    private Exercise testExercise8;
    private Exercise testExercise9;
    int[] arr;


    @Before
    public void setUp() throws Exception {
        arr = {0,0,0,0,0};
        testFactory = new ExerciseFactory();
        testExercise1 = new Exercise(5, 5, 45, ExerciseFactory.SQUAT);
        testExercise2 = new Exercise(3, 8, 45, ExerciseFactory.BARBELL_SHRUGS);
        testExercise3 = new Exercise(2, 10, 45, ExerciseFactory.HYPEREXTENSIONS);
        testExercise4 = new Exercise(1, 5, 45, ExerciseFactory.DEADLIFT);
        testExercise5 = new Exercise(3, 10, 45, ExerciseFactory.CABLE_CRUNCHES);
        testExercise6 = new Exercise(2, 10, 45, ExerciseFactory.HYPEREXTENSIONS,arr);
        testExercise7 = new Exercise(1, 5, 45, ExerciseFactory.DEADLIFT,arr);
        testExercise8 = new Exercise(3, 8, 45, ExerciseFactory.CLOSE_GRIP_BENCH_PRESS,arr);
        testExercise9 = new Exercise(0 ,0 ,0 ,100 , arr);
    }

    @Test
    public void testCreateExercise() throws Exception {
        assertTrue("creating squat should be equal to testExercise1",
                testFactory.createExercise(ExerciseFactory.SQUAT,45).equalTo(
                        testExercise1));
        assertTrue("creating shrug should be equal to testExercise2",
                testFactory.createExercise(ExerciseFactory.BARBELL_SHRUGS,45).equalTo(
                        testExercise2));
        assertTrue("creating hyperextension should be equal to testExercise3",
                testFactory.createExercise(ExerciseFactory.HYPEREXTENSIONS,45).equalTo(
                        testExercise3));
        assertTrue("creating deadlift should be equal to testExercise4",
                testFactory.createExercise(ExerciseFactory.DEADLIFT,45).equalTo(
                        testExercise4));
        assertTrue("creating cable crunch should be equal to testExercise5",
                testFactory.createExercise(ExerciseFactory.CABLE_CRUNCHES,45).equalTo(
                        testExercise5));
        assertTrue("creating bogus exercise should be equal to testExercise1",
                testFactory.createExercise(100,45).equalTo(
                        testExercise1));
    }

    @Test
    public void testCreateExercise1() throws Exception {
        assertTrue("creating hyperextension should be equal to testExercise6",
                testFactory.createExercise(ExerciseFactory.HYPEREXTENSIONS,45,arr).equalTo(testExercise6));
        assertTrue("creating deadlift should be equal to testExercise7",
                testFactory.createExercise(ExerciseFactory.DEADLIFT, 45, arr).equalTo(testExercise7));
        assertTrue("creating close grip bench press should be equal to testExercise8",
                testFactory.createExercise(ExerciseFactory.CLOSE_GRIP_BENCH_PRESS, 45, arr).equalTo(testExercise8));
        assertTrue("creating bogus exercise should be equal to testExercise9",
                testFactory.createExercise(100,0,arr).equalTo(testExercise9));
    }
}
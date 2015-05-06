package com.sounds_good.brrro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pgoggijr on 5/6/15.
 */
public class ExerciseTest {

    private ExerciseFactory $;
    private Exercise exercise1;
    private Exercise exercise2;

    public void setUp() throws Exception {
        this.$ = new ExerciseFactory();
        this.exercise1 = $.createExercise(ExerciseFactory.SQUAT,45);
        this.exercise2 = $.createExercise(ExerciseFactory.BARBELL_SHRUGS,12);
    }

    @Test
    public void testGetSets() throws Exception {
    }

    @Test
    public void testGetReps() throws Exception {
        assertEquals("getReps must be 5", 5, this.exercise1.getReps());
    }

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testGetWeight() throws Exception {

    }

    @Test
    public void testSetWeight() throws Exception {

    }

    @Test
    public void testUpdateSet() throws Exception {

    }

    @Test
    public void testUpdateSet1() throws Exception {

    }
}
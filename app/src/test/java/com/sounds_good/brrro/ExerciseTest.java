package com.sounds_good.brrro;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pgoggijr on 5/6/15.
 */
public class ExerciseTest extends TestCase {

    private ExerciseFactory $;
    private Exercise exercise1;
    private Exercise exercise2;

    public ExerciseTest() {
        super("Exercise Test");
    }

    @Before
    public void setUp() throws Exception {
        this.$ = new ExerciseFactory();
        this.exercise1 = $.createExercise(ExerciseFactory.SQUAT,45);
        this.exercise2 = $.createExercise(ExerciseFactory.BARBELL_SHRUGS,12);
    }

    @Test
    public void testGetSets() throws Exception {
        assertArrayEquals("exercise1.getSets Must be  5 long array of 0's",
                new int[5], exercise1.getSets());
        assertArrayEquals("exercise2.getSets Must be  3 long array of 0's",
                new int[3], exercise2.getSets());
    }

    @Test
    public void testGetReps() throws Exception {
        assertEquals("exercise1.getReps must be 5", 5, this.exercise1.getReps());
        assertEquals("exercise2.getReps must be 8", 8, this.exercise2.getReps());
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals("exercise1.getTypes must be" + ExerciseFactory.SQUAT,
                ExerciseFactory.SQUAT, this.exercise1.getType());
        assertEquals("exercise2.getTypes must be" + ExerciseFactory.BARBELL_SHRUGS,
                ExerciseFactory.SQUAT, this.exercise1.getType());
    }

    @Test
    public void testGetWeight() throws Exception {
        assertEquals("exercise1.getWeight must be 45", 45, this.exercise1.getWeight());
        assertEquals("exercise2.getWeight must be 45", 12, this.exercise2.getWeight());

    }

    @Test
    public void testSetWeight() throws Exception {
        assertEquals("exercise1.setWeight must be true", true, this.exercise1.setWeight(50));
        assertEquals("exercise1.getWeight must be 50", 50, this.exercise1.getWeight());
        assertEquals("exercise1.setWeight must be false", false, this.exercise1.setWeight(-1));
    }

    @Test
    public void testUpdateSet() throws Exception {
        assertEquals("exercise1.updateSet must be 5", 5, this.exercise1.updateSet(0));
        assertEquals("exercise1.updateSet must be -1", -1, this.exercise1.updateSet(5));
        assertEquals("exercise1.updateSet must be -1", -1, this.exercise1.updateSet(-1));
        assertEquals("exercise1's first set must be 5", 5, this.exercise1.getSets()[0]);
        assertEquals("exercise1.updateSet must be 4", 4, this.exercise1.updateSet(0));
        assertEquals("exercise1's first set must be 4", 4, this.exercise1.getSets()[0]);
    }

    @Test
    public void testUpdateSet1() throws Exception {
        assertEquals("exercise1.updateSet must be 5", 5, this.exercise1.updateSet(0,5));
        assertEquals("exercise1.updateSet must be -1", -1, this.exercise1.updateSet(-1,5));
        assertEquals("exercise1.updateSet must be 5", 5, this.exercise1.updateSet(0,5));
        assertEquals("exercise1's first set must be 5", 5, this.exercise1.getSets()[0]);
    }
}
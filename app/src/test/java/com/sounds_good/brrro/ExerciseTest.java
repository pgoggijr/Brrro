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
    private Exercise testExercise;
    private Exercise testExercise1;
    private Exercise testExercise2;
    private Exercise testExercise3;

    public ExerciseTest() {
        super("Exercise Test");
    }

    @Before
    public void setUp() throws Exception {
        this.$ = new ExerciseFactory();
        this.testExercise = $.createExercise(ExerciseFactory.SQUAT,45);
        this.testExercise1 = $.createExercise(ExerciseFactory.SQUAT,45);
        this.testExercise2 = $.createExercise(ExerciseFactory.HYPEREXTENSIONS,45);
        this.testExercise3 = $.createExercise(ExerciseFactory.HYPEREXTENSIONS,45);

    }

    @Test
    public void testGetSets() throws Exception {
        assertArrayEquals("testExercise.getSets Must be  5 long array of 0's",
                new int[5], testExercise.getSets());
    }

    @Test
    public void testGetReps() throws Exception {
        assertEquals("testExercise.getReps must be 5", 5, this.testExercise.getReps());
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals("testExercise.getTypes must be" + ExerciseFactory.SQUAT,
                ExerciseFactory.SQUAT, this.testExercise.getType());
    }

    @Test
    public void testGetWeight() throws Exception {
        assertEquals("testExercise.getWeight must be 45", 45, this.testExercise.getWeight());

    }

    @Test
    public void testSetWeight() throws Exception {
        assertEquals("testExercise.setWeight must be true", true, this.testExercise.setWeight(50));
        assertEquals("testExercise.getWeight must be 50", 50, this.testExercise.getWeight());
        assertEquals("testExercise.setWeight must be false", false, this.testExercise.setWeight(-1));
    }

    @Test
    public void testUpdateSet() throws Exception {
        assertEquals("testExercise.updateSet must be 5", 5, this.testExercise.updateSet(0));
        assertEquals("testExercise.updateSet must be -1", -1, this.testExercise.updateSet(5));
        assertEquals("testExercise.updateSet must be -1", -1, this.testExercise.updateSet(-1));
        assertEquals("testExercise's first set must be 5", 5, this.testExercise.getSets()[0]);
        assertEquals("testExercise.updateSet must be 4", 4, this.testExercise.updateSet(0));
        assertEquals("testExercise's first set must be 4", 4, this.testExercise.getSets()[0]);
    }

    @Test
    public void testUpdateSet1() throws Exception {
        assertEquals("testExercise.updateSet must be 5", 5, this.testExercise.updateSet(0, 5));
        assertEquals("testExercise.updateSet must be -1", -1, this.testExercise.updateSet(-1, 5));
        assertEquals("testExercise.updateSet must be 5", 5, this.testExercise.updateSet(0, 5));
        assertEquals("testExercise's first set must be 5", 5, this.testExercise.getSets()[0]);
    }

    @Test
    public void testEqualTo() throws Exception {
        testExercise.updateSet(0);
        assertEquals("testExercise.equalTo must be false", false,
                this.testExercise.equalTo(this.testExercise1));
        assertEquals("testExercise1.equalTo must be false", false,
                this.testExercise1.equalTo(this.testExercise2));
        assertEquals("testExercise2.equalTo must be true", true,
                this.testExercise2.equalTo(this.testExercise3));
        testExercise2.updateSet(0);
        assertEquals("testExercise2.equalTo must be false", false,
                this.testExercise2.equalTo(this.testExercise3));
    }
}
package com.sounds_good.brrro;

/**
 * Created by Peter on 4/24/2015.
 * This is the most disgusting method of testing ever, I apologize
 * Once I figure out the testing framework we gotta uncomment the package statements at the top of all our classes
 */
public class Tests {
    public static void main(String[] args) {
        ExerciseFactory $ = new ExerciseFactory();

        //Exercise Factory Tests
        System.out.println("EXERCISE FACTORY TESTS\n----------------------------------------");
        System.out.println("Should be 40");
        System.out.println($.createExercise($.BENCH_PRESS, 40).getWeight());
        System.out.println("Should be 3");
        System.out.println($.createExercise($.BENCH_PRESS, 40).getType());
        System.out.println("Should be 5 long array of 1's");
        int[] arr = new int[5];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        $.createExercise($.BENCH_PRESS, 40, arr).printExercise();
        System.out.println("Should be 1");
        System.out.println($.createExercise(10000, 40).getType());

        //Exercise Tests
        System.out.println();
        System.out.println("EXERCISE TESTS\n----------------------------------------");
        Exercise squatTest = $.createExercise($.SQUAT, 50);
        Exercise badParams = new Exercise(-1,-1,-1,1);
        System.out.println("Should be array of all 0's");
        squatTest.printExercise();
        System.out.println("First item should be 5");
        squatTest.updateSet(0);
        squatTest.printExercise();
        System.out.println("Second item should be 4");
        squatTest.updateSet(1);
        squatTest.updateSet(1);
        squatTest.printExercise();
        System.out.println("Third item should be 0");
        squatTest.updateSet(2);
        squatTest.updateSet(2);
        squatTest.updateSet(2);
        squatTest.updateSet(2);
        squatTest.updateSet(2);
        squatTest.updateSet(2);
        squatTest.printExercise();
        System.out.println("Fourth item should be 3");
        squatTest.updateSet(3,3);
        squatTest.printExercise();
        System.out.println("Last item should be 5");
        squatTest.updateSet(4);
        squatTest.printExercise();
        System.out.println("Same as previous");
        int[] toPrint = squatTest.getSets();
        System.out.print("[");
        for(int i = 0; i < toPrint.length; i++) {
            System.out.print(toPrint[i]);
            if(i != toPrint.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
        System.out.println("Should be 1");
        System.out.println(badParams.getSets().length);
        System.out.println("Should be 10");
        System.out.println(badParams.getWeight());
    }
}

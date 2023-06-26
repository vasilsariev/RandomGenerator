package org.generator.main;

import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {

    @Test
    void testInputOfEqualLengthArrays() {
        assertDoesNotThrow(() -> new RandomGenerator(new int[] {0, 1, 2, 3}, new float[] {0.25f, 0.25f, 0.25f, 0.25f}));
    }

    @Test
    void testInputOfDifferentLengthArrays() {
        assertThrows(RuntimeException.class, () -> new RandomGenerator(new int[] {1, 2}, new float[] {0.3f, 0.3f, 0.3f}));
    }

    @Test
    void testForNullArrays() {
        assertThrows(RuntimeException.class, () -> new RandomGenerator(null, null));
    }

    @Test
    void testForNegativeProbabilities() {
        assertThrows(RuntimeException.class, () -> new RandomGenerator(new int[] {1, 2}, new float[] {0.5f, -0.5f}));
    }

    @Test
    void testNextNumResults(){
        int[] numbers = {1, 2, 3, 4, 5};
        float[] probabilities = {0.05f, 0.15f, 0.3f, 0.2f, 0.3f};

        var generator = new RandomGenerator(numbers, probabilities);

        int tries = 100000;
        Map<Integer, Integer> results = new HashMap<>();

        for (int i = 0; i < tries; i++) {
            int number = generator.nextNum();
            results.put(number, results.getOrDefault(number, 0) + 1);
        }
        for (int i = 0; i < numbers.length; i++) {
            int appearances = results.getOrDefault(numbers[i], 0);
            // here I get the float number from the normalized probabilities
            assertTrue((Math.abs(appearances - (tries * generator.getProbabilities()[i])) / tries) <= 0.03);

        }




    }




}
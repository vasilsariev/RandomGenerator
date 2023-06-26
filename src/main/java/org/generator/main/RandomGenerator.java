package org.generator.main;

import java.util.Random;

public class RandomGenerator {
    // Values that may be returned by nextNum();
    private final int[] randomNums;

    // Probability of the occurrence of randomNums;
    private final float[] probabilities;

    public final Random random;

    public RandomGenerator(int[] randomNums, float[] probabilities) {
        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.random = new Random();
        checkArrays(randomNums, probabilities);

        normalizeProbabilities();
    }

    public int nextNum() {
        float chanceNumber = random.nextFloat();
        float sumOfProbabilities = 0.0f;
        for (int i = 0; i < randomNums.length; i++) {
            sumOfProbabilities += probabilities[i];
            if(chanceNumber <= sumOfProbabilities) {
                return randomNums[i];
            }

        }
        // Default return if no match (should not be reached due to normalized probabilities)
        return randomNums[randomNums.length - 1];
    }

    // Normalizes the probabilities so they sum up to 1
    private void normalizeProbabilities() {
        float sum = 0.0f;

        //summing all the probabilities;
        for (float number : probabilities) {
            sum += number;
        }
        if(sum == 1) {
            return;
        }

        //assigning their new value;
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] = probabilities[i] / sum;
        }
    }

    public float[] getProbabilities() {
        return probabilities;
    }

    private void checkArrays(int[] nums, float[] probabilities){
        if(nums == null || probabilities == null) {
            throw new RuntimeException("Arrays can't be empty");
        }
        if(!Util.noNegativeFloatNumbers(probabilities)) {
            throw new RuntimeException("No negative numbers allowed in the probabilities arrays");
        }
        if(nums.length != probabilities.length) {
            throw new RuntimeException("Both arrays should have the same length");
        }
    }
}

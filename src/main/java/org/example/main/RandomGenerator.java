package org.example.main;

import java.util.Random;

public class RandomGenerator {
    // Values that may be returned by nextNum();
    private int[] randomNums;

    // Probability of the occurrence of randomNums;
    private float[] probabilities;

    public final Random random;

    public RandomGenerator(int[] randomNums, float[] probabilities) {
        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.random = new Random();

        if(randomNums.length != probabilities.length) {
            throw new RuntimeException("Both arrays should have the same length");
        }
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
        return randomNums[randomNums.length - 1];
    }

    private void normalizeProbabilities() {
        float sum = 0.0f;

        //summing all the probabilities;
        for (float number : probabilities) {
            sum += number;
        }

        //assigning their new value;
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] = probabilities[i] / sum;
        }
    }

    public float[] getProbabilities() {
        return probabilities;
    }
}

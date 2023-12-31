package org.generator;

import org.generator.main.RandomGenerator;
import org.generator.main.Util;


import java.util.HashMap;
import java.util.Map;



public class Demo {
    public static void main(String[] args) {


        //Request user input for integers and their probabilities;
        int[] numsArray = Util.askUserForIntArray();
        float[] probabilities = Util.askUserForFloatArray();

        RandomGenerator generator = new RandomGenerator(numsArray, probabilities);




        // Store the generated results;
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            int number = generator.nextNum();
            results.put(number, results.getOrDefault(number, 0) + 1);


        }
        // print the results
        for(Map.Entry<Integer, Integer> map : results.entrySet()) {
            System.out.println("number: " + map.getKey() + " : " + map.getValue() + " times");
        }
    }
}
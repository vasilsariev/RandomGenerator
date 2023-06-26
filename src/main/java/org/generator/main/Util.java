package org.generator.main;

import java.util.Scanner;

public class Util {
    private final static String REGEX_LIST_INT = "^(\\d+(, \\d+)*)$";
    private final static String REGEX_LIST_FLOAT = "^(\\d+(\\.\\d+)?(, \\d+(\\.\\d+)?)*,?)$";

    private final static String WARNING_FLOAT_LIST = "You should enter only float numbers divided by ', '(comma and space). CAN'T BE EMPTY. Only positive numbers allowed ";
    private final static String INSTRUCTIONS_FLOAT_LIST = "Enter a list of float numbers divided by ', '(comma and space). Every number should correspond to an integer from the first list";

    private final static String WARNING_INT_LIST = "You should enter only whole numbers divided by ', '(comma and space). CAN'T BE EMPTY ";
    private final static String INSTRUCTIONS_INT_LIST = "Enter a list of whole numbers divided by ', '(comma and space) ";

    public static int[] askUserForIntArray() {
        String input = getUserInput(REGEX_LIST_INT, WARNING_INT_LIST, INSTRUCTIONS_INT_LIST);
        return parseStringToIntArray(input);
    }

    public static float[] askUserForFloatArray() {
        String input = getUserInput(REGEX_LIST_FLOAT, WARNING_FLOAT_LIST, INSTRUCTIONS_FLOAT_LIST);
        return parseStringToFloatArray(input);
    }

    private static String getUserInput(String regex,  String warning, String instruction) {
        Scanner sc = new Scanner(System.in);
        System.out.println(instruction);
        String input = sc.nextLine();
        while(!input.matches(regex)) {
            System.out.println(warning);
            input = sc.nextLine();
        }
        return input;

    }

    private static int[] parseStringToIntArray(String strInput) {
        String[] strArray = strInput.split(", ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }

    private static float[] parseStringToFloatArray(String strInput) {
        String[] strArray = strInput.split(", ");
        float[] floatArray = new float[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            floatArray[i] = Float.parseFloat(strArray[i]);
        }
        return floatArray;
    }

    public static boolean noNegativeFloatNumbers(float[] floatArr) {
        for(float number : floatArr){
            if(number < 0) {
                return false;
            }
        }
        return true;
    }

}

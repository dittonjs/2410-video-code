package com.usu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String firstName = "Joseph";
        int number = 10;
        double number2 = 22.2;

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(22);
        numbers.add(454);
        numbers.add(37);

        if (numbers.size() > 1000) {
            System.out.println("We have a lot of numbers!");
        } else if (numbers.size() > 0) {
            System.out.println("We have a few numbers");
        } else {
            System.out.println("There are no numbers");
        }

        for (int i = 0; i < numbers.size(); i ++) {
            // do something with numbers
        }

        boolean shouldContinue = true;
        while (shouldContinue) {
            // do something in here
        }

        for (int value : numbers) {
            // do something with value
        }
    }

    public static int doSomething(int numTimes) {
        // do something in here
        return 0;
    }
}

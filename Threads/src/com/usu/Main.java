package com.usu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int userInput = 0;
        while(userInput != -1) {
            System.out.println("input a number > 0");
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
            new Counter(userInput);
        }
    }
}

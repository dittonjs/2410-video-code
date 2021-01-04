package com.usu;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(factorial(10));
        System.out.println(fib(100));
    }

//    10 * 9!
//    factorial(n) = n * factorial(n - 1)
    public static int factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }

    // f(n) = f(n - 1) + f(n - 2)
    public static int fib(int value) {
        if (value == 1) return 1;
        if (value == 2) return 2;
        return fib(value - 1) + fib(value - 2);
    }
}

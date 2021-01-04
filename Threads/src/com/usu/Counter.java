package com.usu;

public class Counter implements Runnable{
    private int maxNumber;
    private int currentNumber = 0;
    public Counter(int number) {
        maxNumber = number;
        new Thread(this).start();
    }
    public void run() {
        while (currentNumber < maxNumber) {
            currentNumber ++;
            System.out.println(currentNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

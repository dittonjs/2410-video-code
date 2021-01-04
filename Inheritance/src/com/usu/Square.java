package com.usu;

public class Square extends Shape {
    private double sideLength;
    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return sideLength * 4;
    }

    public void draw() {
        // draw a square
        System.out.println("Drawing a square!");
    }
}

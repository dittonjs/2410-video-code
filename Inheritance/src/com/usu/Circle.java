package com.usu;

public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public void draw() {
        // draws a circle
        System.out.println("Drawing a circle");
    }
}

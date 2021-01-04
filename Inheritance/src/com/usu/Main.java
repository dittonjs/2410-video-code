package com.usu;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Circle circle = new Circle(10.5);
        Square square = new Square(7.3);
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(circle);
        drawables.add(square);
        drawables.add(new Square(2));
        drawables.add(new Square(4.2));
        drawables.add(new Robot());

        for (Drawable shape: drawables) {
            shape.draw();
        }
    }
}

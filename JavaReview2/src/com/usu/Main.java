package com.usu;

import com.usu.animals.Cat;
import com.usu.animals.Dog;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Dog myDog = new Dog();
        Cat myCat = new Cat();
        Cat myOtherCat = new Cat();
        myDog.speak();
        ArrayList<Speakable> things = new ArrayList<>();
        things.add(myDog);
        things.add(myCat);
        things.add(new Robot());

        for(Speakable thing: things) {
            thing.speak();
        }
    }
}

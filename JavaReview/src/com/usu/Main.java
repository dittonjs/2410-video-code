package com.usu;

import com.usu.animals.Animal;
import com.usu.animals.Cat;
import com.usu.animals.Dog;
import com.usu.interfaces.Speakable;
import com.usu.machines.Robot;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Dog myDog = new Dog();
        Cat myCat = new Cat();
        myDog.speak();
        ArrayList<Speakable> thingsThatSpeak = new ArrayList<>();
        thingsThatSpeak.add(myDog);
        thingsThatSpeak.add(myCat);
        thingsThatSpeak.add(new Robot());
        for(Speakable thing: thingsThatSpeak) {
            thing.speak();
        }
    }
}

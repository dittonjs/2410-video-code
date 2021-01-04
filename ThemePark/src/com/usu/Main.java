package com.usu;

import com.usu.rides.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create scanner
        Scanner scanner = new Scanner(System.in);
        // setup lists for people and rides
        ArrayList<Person> peoplePool = new ArrayList<>();
        ArrayList<Ride> rides = new ArrayList<>();

        //create rides
        rides.add(new LogRide());
        rides.add(new MerryGoRound());
        rides.add(new RollerCoaster());
        rides.add(new SwingingShip());
        rides.add(new TiltAWhirl());

        // generate 40 people
        for (int i = 0; i < 40; i++) {
            peoplePool.add(new Person(i));
        }

        // add people to ride lines the process the lines
        while (true) {
            // add people to the ride queues
            while (peoplePool.size() > 0) {
                Person person = peoplePool.remove(0);
                int rideIndex = (int)(Math.random() * (rides.size()));
                Ride ride = rides.get(rideIndex);

                if (!rides.get(rideIndex).enqueueRider(person)) {
                    System.out.printf("Person %d left the park because lines were too long or they ran out of money. \n", person.getId());
                } else {
                    System.out.printf("Person %d got on the %s\n", person.getId(), ride.getRideName());
                }
            }
            // TODO: process each ride and report
            for (Ride ride : rides) {
                ArrayList<Person> riders = ride.performCycle();
                System.out.printf("%s processed %d riders \n", ride.getRideName(), riders.size());
                peoplePool.addAll(riders);
            }
            System.out.printf("There are %d people who will get on a new ride. \n", peoplePool.size());
            System.out.println("Press enter to continue or type stop to break");
            String input = scanner.nextLine();
            if (input.equals("stop")) break;
        }
    }
}

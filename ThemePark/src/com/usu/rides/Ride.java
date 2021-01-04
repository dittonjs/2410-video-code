package com.usu.rides;

import com.usu.Person;
import com.usu.RideQueue;

import java.util.ArrayList;

public abstract class Ride {

    private RideQueue rideQueue = new RideQueue();
    public boolean enqueueRider(Person person) {

        if (rideQueue.numberOfQueuedRiders() >= getMaxQueueLength()) return false;
        if (!person.charge(getPrice())) return false;

        rideQueue.enqueueRider(person);
        // get max queue length and check the length of the queue.
        return true;
    }

    public ArrayList<Person> performCycle() {
        ArrayList<Person> dequeuedRiders = new ArrayList<>();
        int dequeuedRidersCount = 0;
        while(dequeuedRidersCount < getNumberOfSeats()) {
            Person rider = rideQueue.dequeueRider();
            if (rider == null) break;
            dequeuedRiders.add(rider);
            dequeuedRidersCount += 1;
        }
        // return an array list of people who just finished the ride
        return dequeuedRiders;
    }

    public abstract String getRideName();

    protected abstract int getPrice();

    protected abstract int getMaxQueueLength();

    protected abstract int getNumberOfSeats();
}

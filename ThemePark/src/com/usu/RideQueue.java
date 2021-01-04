package com.usu;

import java.util.ArrayList;

public class RideQueue {
    private ArrayList<Person> queue = new ArrayList<>();

    public void enqueueRider(Person rider) {
        queue.add(rider);
    }

    public int numberOfQueuedRiders() {
        return queue.size();
    }

    public Person dequeueRider() {
        if (queue.size() == 0) return null;
        return queue.remove(0);
    }

    public class QueueNode {
        public Person rider;
        public QueueNode next;
    }
}

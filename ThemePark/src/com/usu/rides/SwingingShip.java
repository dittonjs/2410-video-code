package com.usu.rides;

public class SwingingShip extends Ride{
    @Override
    public String getRideName() {
        return "Swinging Ship";
    }

    @Override
    protected int getPrice() {
        return 10;
    }

    @Override
    protected int getMaxQueueLength() {
        return 20;
    }

    @Override
    protected int getNumberOfSeats() {
        return 10;
    }
}

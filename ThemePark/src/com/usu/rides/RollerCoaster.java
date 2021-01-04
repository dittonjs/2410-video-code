package com.usu.rides;

public class RollerCoaster extends Ride {
    @Override
    public String getRideName() {
        return "Roller Coaster";
    }

    @Override
    protected int getPrice() {
        return 15;
    }

    @Override
    protected int getMaxQueueLength() {
        return 25;
    }

    @Override
    protected int getNumberOfSeats() {
        return 10;
    }
}

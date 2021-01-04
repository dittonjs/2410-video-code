package com.usu.rides;

public class TiltAWhirl extends Ride{
    @Override
    public String getRideName() {
        return "Tile A Whirl";
    }

    @Override
    protected int getPrice() {
        return 5;
    }

    @Override
    protected int getMaxQueueLength() {
        return 12;
    }

    @Override
    protected int getNumberOfSeats() {
        return 3;
    }
}

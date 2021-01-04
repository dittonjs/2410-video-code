package com.usu.rides;

public class LogRide extends Ride{
    @Override
    public String getRideName() {
        return "Log Ride";
    }

    @Override
    protected int getPrice() {
        return 10;
    }

    @Override
    protected int getMaxQueueLength() {
        return 15;
    }

    @Override
    protected int getNumberOfSeats() {
        return 2;
    }
}

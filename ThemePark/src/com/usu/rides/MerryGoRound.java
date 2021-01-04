package com.usu.rides;

public class MerryGoRound extends Ride {
    @Override
    public String getRideName() {
        return "Merry Go Round";
    }

    @Override
    protected int getPrice() {
        return 2;
    }

    @Override
    protected int getMaxQueueLength() {
        return 10;
    }

    @Override
    protected int getNumberOfSeats() {
        return 5;
    }
}

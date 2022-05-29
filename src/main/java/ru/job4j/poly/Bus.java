package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Bus rides");
    }

    @Override
    public void passengers(int count) {
        System.out.println("There are " + count + " passengers in the bus");
    }

    @Override
    public int refill(int liters) {
        return liters * 50;
    }
}

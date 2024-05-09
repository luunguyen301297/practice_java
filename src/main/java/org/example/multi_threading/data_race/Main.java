package org.example.multi_threading.data_race;

import java.util.stream.IntStream;

public class Main {
    public static void main(String... args) throws Exception {
        final Runnable increaseCounterFunc = () -> IntStream
                .range(0, 100)
                .forEach(MutualExclusion::increaseCounter);

        final Thread first = new Thread(increaseCounterFunc);
        final Thread second = new Thread(increaseCounterFunc);

        first.start();
        second.start();

        first.join();
        second.join();

        System.err.println(MutualExclusion.COUNTER);
    }
}

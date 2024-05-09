package org.example.multi_threading.race_condition;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String... args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final List<Shopper> shoppers = IntStream.range(0, 6)
                .mapToObj(Shopper::new)
                .toList();
        shoppers.forEach(Thread::start);
        for (var shopper : shoppers) {
            shopper.join();
        }
        System.out.println("Total packs: " + Shopper.MASK_PACK_COUNT);
        System.err.println(System.currentTimeMillis() - start);
    }
}

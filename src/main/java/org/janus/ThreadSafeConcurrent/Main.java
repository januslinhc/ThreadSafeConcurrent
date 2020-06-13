package org.janus.ThreadSafeConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // this demonstration shows how non-blocking works in the multi-threading environment
        final Integer integer = new Integer();
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        // create 2000 threads for increasing this integer at the same time
        for (int i = 0; i < 2000; i++) {
            executorService.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(String.format("%s start", threadName));
                integer.getAndIncrease();
                System.out.println(String.format("%s end", threadName));
            });
        }
        // wait until all threads completed
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        // print out the result
        // i expect the result should be 2000
        System.out.println(String.format("Result = %s", integer.get()));
    }
}

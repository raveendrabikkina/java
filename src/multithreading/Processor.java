package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Processor {

    private static int i = 1;
    private static int size = 10000;
    private static final List<String> resourceToBeConsumed = new ArrayList<>(size);
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Processor() {
        for (int j = 1; j <= size; j++) {
            resourceToBeConsumed.add(Integer.toString(j));
        }
        System.out.println(resourceToBeConsumed.size());
    }

    void remove() {
        readWriteLock.writeLock().lock();
        resourceToBeConsumed.remove(Integer.toString(i));
        i++;
        readWriteLock.writeLock().unlock();
    }

    static void process() {
        Processor processor = new Processor();
        final int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int k = 1; k <= size; k++) {
            Task command = new Task(processor);
            executorService.execute(command);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!executorService.isTerminated()) {

        }
        System.out.println("All tasks are finished!" + resourceToBeConsumed);

    }
}

package multithreading;

public class Task implements Runnable {

    private final Processor processor;

    Task(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        processor.remove();
    }
}
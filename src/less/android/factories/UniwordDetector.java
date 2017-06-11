package less.android.factories;

import less.android.interfaces.Detector;

import java.util.concurrent.*;

public class UniwordDetector extends Thread implements Detector {
    private final String[] files;
    private ConcurrentHashMap<String, Boolean> words = new ConcurrentHashMap<>();
    private final ExecutorService poolExecutor;

    public UniwordDetector(String[] files, Class reader) {
        poolExecutor = Executors.newWorkStealingPool(10);
        this.files = files;
    }

    @Override
    synchronized public void addWord(String word) {
        /*System.out.printf(
                "%s try to add a word: %s\n",
                Thread.currentThread().getName(),
                word
        );*/
        if (! words.containsKey(word)) {
            words.put(word, true);
        }
        else {
            System.out.printf("Word %s is not unique.\n", word);
            poolExecutor.shutdownNow();
            this.interrupt();
        }
    }

    @Override
    public void run() {
        for (String file: files) {
            poolExecutor.execute(new WordFileReader(file, this));
        }

        poolExecutor.shutdown();

        while (! poolExecutor.isTerminated()) {
            synchronized (poolExecutor) {
                try {
                    poolExecutor.wait();
                } catch (InterruptedException e) {
                    System.out.println("Uniword was finish its work.");
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " is finished. With " + words.size() + " words");
    }
}

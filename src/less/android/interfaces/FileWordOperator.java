package less.android.interfaces;

import less.android.factories.WordFileReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class FileWordOperator extends Thread {
    private final String[] files;
    protected final ExecutorService poolExecutor;

    public FileWordOperator(String[] files) {
        poolExecutor = Executors.newWorkStealingPool(10);
        this.files = files;
    }

    public abstract void addWord(String word);

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
                    break;
                }
            }
        }

        System.out.println(getName() + " is processed files:");
        for (String file: files) {
            System.out.printf("     %s\n", file);
        }

    }
}

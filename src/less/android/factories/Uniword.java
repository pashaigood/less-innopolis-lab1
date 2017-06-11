package less.android.factories;

import less.android.interfaces.FileWordOperator;
import less.android.utils.WordChecker;

import java.util.concurrent.*;

public class Uniword extends FileWordOperator {
    private ConcurrentHashMap<String, Boolean> words = new ConcurrentHashMap<>();

    public Uniword(String[] files) {
        super(files);
    }

    @Override
    synchronized public void addWord(String word) {
        if (! WordChecker.check(word)) {
            poolExecutor.shutdownNow();
            System.out.printf("Word %s is not allowed.\n", word);
        }

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
        super.run();
        System.out.println(Thread.currentThread().getName() + " is finished. With " + words.size() + " words\n\n");
    }

}

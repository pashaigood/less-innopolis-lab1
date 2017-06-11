package less.android.factories;

import less.android.interfaces.FileWordOperator;
import less.android.utils.WordChecker;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounter extends FileWordOperator {
    private ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap<>();

    public WordCounter(String[] files) {
        super(files);
    }

    @Override
    synchronized public void addWord(String word) {
        if (! WordChecker.check(word)) {
            poolExecutor.shutdownNow();
            System.out.printf("Word %s is not allowed.\n", word);
        }

        Integer count;
        if ((count = words.get(word)) == null) {
            words.put(word, 1);
        }
        else {
            words.put(word, ++count);
        }
    }

    @Override
    public void run() {
        super.run();
        printResult();
    }

    // TODO: Thick about sorting.
    private void printResult() {
        String leftAlignFormat = "| %-15s | %-4s |%n";

        System.out.format("+-----------------+-------+%n");
        System.out.format(leftAlignFormat, "Word", "Count");
        System.out.format("+-----------------+-------+%n");
        Iterator<Map.Entry<String, Integer>> iterator = words.entrySet().iterator();
        Map.Entry entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            System.out.format(leftAlignFormat, entry.getKey(), entry.getValue());
        }
        System.out.format("+-----------------+-------+%n");
        System.out.printf(Thread.currentThread().getName() + ": has %s words\n\n\n", words.size());
    }
}

package less.android;

import less.android.factories.Uniword;
import less.android.factories.WordCounter;

public class Main {

    public static void main(String[] args) {
        tryCorrectCollection();
        tryIncorrectCollection();
        tryIncorrectSymbols();
        tryWithUnexistingFile();
        tryCountWords();
    }

    private static void tryWithUnexistingFile() {
        createUniword(new String[] {
                "./resources/notexists/1.txt",
                "./resources/unique/two.txt",
                "./resources/notexists/2.txt"
        }, "With not existing files");
    }

    private static void tryIncorrectSymbols() {
        createUniword(new String[] {
                "./resources/incorrect/symbols.txt",
                "./resources/incorrect/symbols_2.txt"
        }, "Incorrect symbol");
    }

    private static void tryCorrectCollection() {
        createUniword(new String[] {
                "./resources/unique/one.txt",
                "./resources/unique/two.txt",
                "./resources/unique/three.txt"
        }, "Correct uniword");
    }

    private static void tryIncorrectCollection() {
        createUniword(new String[] {
                "./resources/notunique/one.txt",
                "./resources/notunique/two.txt",
                "./resources/notunique/three.txt"
        }, "Incorrect uniword");
    }

    private static void createUniword(String[] files, String name) {
        Uniword uniwordDetector = new Uniword(files);
        uniwordDetector.setName(name);
        uniwordDetector.start();
    }

    private static void tryCountWords() {
        WordCounter wordCounter = new WordCounter(new String[] {
                "./resources/notunique/one.txt",
                "./resources/notunique/three.txt"
        });
        wordCounter.setName("Word counter");
        wordCounter.start();
    }
}

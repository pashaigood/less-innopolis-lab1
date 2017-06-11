package less.android;

import less.android.factories.UniwordDetector;

public class Main {

    public static void main(String[] args) {
        tryIncorrectSymbols();
        tryCorrectCollection();
        tryIncorrectCollection();
    }

    private static void tryIncorrectSymbols() {
        createUniword(new String[] {
                "./resources/incorrect/symbols.txt",
                "./resources/incorrect/symbols_2.txt"
        }, "Incorrect symbol");
    }

    private static void tryCorrectCollection() {
        createUniword(new String[] {
                "./resources/correct/one.txt",
                "./resources/correct/two.txt",
                "./resources/correct/three.txt"
        }, "Correct uniword");
    }

    private static void tryIncorrectCollection() {
        createUniword(new String[] {
                "./resources/incorrect/one.txt",
                "./resources/incorrect/two.txt",
                "./resources/incorrect/three.txt"
        }, "Incorrect uniword");
    }

    private static void createUniword(String[] files, String name) {
        UniwordDetector uniwordDetector = new UniwordDetector(files);
        uniwordDetector.setName(name);
        uniwordDetector.start();
    }
}

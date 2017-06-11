package less.android;

import less.android.factories.UniwordDetector;
import less.android.factories.WordFileReader;

public class Main {

    public static void main(String[] args) {
        tryCorrectCollection();
    }

    private static void tryCorrectCollection() {
        UniwordDetector correctUniwordDetector = new UniwordDetector(new String[] {
                "./resources/correct/one.txt",
                "./resources/correct/two.txt",
                "./resources/correct/three.txt"
        }, WordFileReader.class);
        correctUniwordDetector.start();
    }

    private static void tryIncorrectCollection() {
        UniwordDetector incorrectUniwordDetector = new UniwordDetector(new String[] {
                "./resources/incorrect/one.txt",
                "./resources/incorrect/two.txt"
        }, WordFileReader.class);
        incorrectUniwordDetector.start();
    }
}

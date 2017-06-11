package less.android.factories;

import less.android.interfaces.Detector;
import less.android.utils.WordChecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WordFileReader extends Thread {
    private Detector detector;
    private String fileName;

    public WordFileReader(String fileName, Detector detector) {
        this.detector = detector;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try(BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = file.readLine()) != null) {
                for (String word : WordChecker.getWordsFromString(line)) {
                    try {
                        Thread.sleep(25);
                        detector.addWord(word);
                    } catch (InterruptedException e) {
                        System.out.println(fileName + " is not required anymore.");
                        return;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.printf("%s. is not exists.\n", fileName);
            return;
        } catch (Exception e) {
            System.err.printf("Some problem occurred while reading %s.\n", fileName);
            return;
        }

        System.out.printf("%s is readed.\n", fileName);
    }
}

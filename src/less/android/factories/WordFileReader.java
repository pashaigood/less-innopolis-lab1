package less.android.factories;

import less.android.interfaces.Detector;
import less.android.utils.WordChecker;

import java.io.BufferedReader;
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
                try {
                    for (String word : WordChecker.getWordsFromString(line)) {
                        if (Thread.interrupted()) {
                            return;
                        }
                        Thread.sleep(25);
                        detector.addWord(word);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Word file reader " + fileName + ": is not required anymore.");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    interrupt();
                    return;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

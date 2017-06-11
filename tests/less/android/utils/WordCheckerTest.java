package less.android.utils;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordCheckerTest {
    @Test
    public void checkCorrect() throws Exception {
        String[] mockCorrectWordList = {
            "привет",
            "По.ка",
            "Хел,-ло",
            "Гуу;.дБай",
            "Приет;Привет",
            "Пока-Привет",
            ";;",
            "..",
            "-.-;"
        };

        ArrayList<String> words = WordChecker.getWordsFromString(String.join(" ", mockCorrectWordList));
        assertArrayEquals(mockCorrectWordList, words.toArray());

        assertArrayEquals(
            new String[] {"Привет", "Привет2"},
            WordChecker.getWordsFromString("  Привет  Привет2").toArray()
        );

        assertArrayEquals(
            new String[] {"Привет", "Привет2"},
            WordChecker.getWordsFromString("  Привет\n  \tПривет2\r").toArray()
        );
    }

    @Test
    public void checkIncorrect() throws Exception {
        ArrayList<String[]> mocks = new ArrayList<>();

        mocks.add(new String[] {"Привет", "Пока", ";"});
        mocks.add(new String[] {"Привет", ";", "Привет"});
        mocks.add(new String[] {"Привет", ".", "Привет"});
        mocks.add(new String[] {"Привет", "-", "Привет"});
        mocks.add(new String[] {"Привет", "ПоIка", "Привет"});

        for (String[] mock: mocks) {
            try {
                WordChecker.getWordsFromString(String.join(" ", mock));
                assertTrue("Should throw on incorrect symbol in: " + String.join(", ", mock), false);
            } catch (Exception e) {}
        }
    }
}
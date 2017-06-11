package less.android.utils;

import org.junit.Test;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class WordCheckerTest {
    @Test
    public void wordsShouldBeTrimmed() throws Exception {
        String[] mocks = {
            "привет  ",
            "  По.ка",
            "Хел,-ло  ",
            "Гуу;.дБай\n\t",
            "\tПриет;Привет",
            "dsfds fsd\tfdsf\n\rfdf"
        };
        Pattern isTrimmed = Pattern.compile("[\\S]+");

        String [] words = WordChecker.getWordsFromString(String.join(" ", mocks));
        for (String word: words) {
            assertTrue("Word: " + word + "should be trimmed.", isTrimmed.matcher(word).matches());
        }
    }

    @Test
    public void checkCorrect() throws Exception {
        String[] mocks = {
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

        for (String mock: mocks) {
            assertTrue("Word:" + mock + " should be not allowed.", WordChecker.check(mock));
        }
    }

    @Test
    public void checkIncorrect() throws Exception {
        String[] mocks = {";", " ", "_=", " dsdd ds", "авав авав"};

        for (String mock: mocks) {
            assertTrue("Word:" + mock + " should not allowed.", !WordChecker.check(mock));
        }
    }
}
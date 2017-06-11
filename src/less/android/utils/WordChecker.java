package less.android.utils;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class WordChecker {
    private static Pattern deniedPattern = Pattern.compile("[;,.\\-]");
    private static Pattern allowedPattern = Pattern.compile("[А-яЁё;,.0-9_\\-]+");

    public static ArrayList<String> getWordsFromString(String input) throws Exception {
        ArrayList<String> result = new ArrayList<>();

        for (String word: input.trim().split("\\s+")) {
            if (! check(word)) {
                throw new Exception("Incorrect character in word: " + word);
            }
            result.add(word);
        }

        return result;
    }

    private static boolean check(String word) {
        return !deniedPattern.matcher(word).matches() && allowedPattern.matcher(word).matches();
    }
}

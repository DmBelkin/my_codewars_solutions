import java.util.TreeSet;

public class CountingDuplicates_6KYU {

//    DESCRIPTION:
//    Count the number of Duplicates
//    Write a function that will return the count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.
//
//    Example
//            "abcde" -> 0 # no characters repeats more than once
//            "aabbcde" -> 2 # 'a' and 'b'
//            "aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
//            "indivisibility" -> 1 # 'i' occurs six times
//            "Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
//            "aA11" -> 2 # 'a' and '1'
//            "ABBA" -> 2 # 'A' and 'B' each occur twice

    public static int duplicateCount(String text) {
        text = text.toLowerCase();
        int check = 0;
        TreeSet<Character> collect = new TreeSet<>();
        for (int i = 0; i <= text.length() - 1; i ++) {
            for (int j = 0; j <= text.length() - 1; j ++) {
                if (text.charAt(i) == text.charAt(j)) {
                    check++;
                    if (check > 1) {
                        collect.add(text.charAt(i));
                    }
                }
            }
            check = 0;
        }
        return collect.size();
    }
}

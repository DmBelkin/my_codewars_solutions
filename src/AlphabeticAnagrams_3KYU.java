import java.math.BigInteger;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AlphabeticAnagrams_3KYU {

//    DESCRIPTION:
//    Consider a "word" as any sequence of capital letters A-Z (not limited to just "dictionary words"). For any word with at least two different letters, there are other words composed of the same letters but in a different order (for instance, STATIONARILY/ANTIROYALIST, which happen to both be dictionary words; for our purposes "AAIILNORSTTY" is also a "word" composed of the same letters as these two).
//
//    We can then assign a number to every word, based on where it falls in an alphabetically sorted list of all words made up of the same group of letters. One way to do this would be to generate the entire list of words and find the desired one, but this would be slow if the word is long.
//
//    Given a word, return its number. Your function should be able to accept any word 25 letters or less in length (possibly with some letters repeated), and take no more than 500 milliseconds to run. To compare, when the solution code runs the 27 test cases in JS, it takes 101ms.
//
//    For very large words, you'll run into number precision issues in JS (if the word's position is greater than 2^53). For the JS tests with large positions, there's some leeway (.000000001%). If you feel like you're getting it right for the smaller ranks, and only failing by rounding on the larger, submit a couple more times and see if it takes.
//
//    Python, Java and Haskell have arbitrary integer precision, so you must be precise in those languages (unless someone corrects me).
//
//    C# is using a long, which may not have the best precision, but the tests are locked so we can't change it.
//
//    Sample words, with their rank:
//    ABAB = 2
//    AAAB = 1
//    BAAA = 4
//    QUESTION = 24572
//    BOOKKEEPER = 10743


    public static BigInteger listPosition(String word) {
        System.out.println(word);
        String[] array = word.split("");
        Arrays.sort(array);
        String anagram = "";
        BigInteger result = new BigInteger("1");
        for (int s = 0; s <= array.length - 1; s++) {
            if (anagram.equals(word)) {
                break;
            }
            if (!(anagram.concat(array[s])).
                    equals(word.concat(" ").substring(0, anagram.length() + 1)) && !array[s].equals("")) {
                BigInteger integer =
                        getFactorial(((word.length() - (anagram.length() + 1)))).divide(searchDuplicate(anagram, word));
                result = result.add(integer.abs());
            } else if ((anagram.concat(array[s]).equals(word.concat("").substring(0, anagram.length() + 1)))) {
                anagram += array[s];
                array[s] = "";
                s = -1;
            }
        }

        return result;
    }

    public static BigInteger searchDuplicate(String anagram, String word) {
        String sub = word.substring(anagram.length());
        Set<String> collect = Arrays.stream(sub.split("")).collect(Collectors.toSet());
        BigInteger result = new BigInteger("1");
        int count = 0;
        for (String letter : collect) {
            for (int j = 0; j <= sub.length() - 1; j++) {
                if (letter.equals("" + sub.charAt(j))) {
                    count++;
                }
            }
            result = result.multiply(getFactorial(count));
            count = 0;
        }
        return result;
    }

    public static BigInteger getFactorial(long index) {
        BigInteger factorial = new BigInteger("1");
        for (long i = 1; i <= index; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }


}

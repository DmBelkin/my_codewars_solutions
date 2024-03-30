import java.util.HashSet;
import java.util.Set;

public class CatchingCarMileageNumbers_4KYU {

    public static int isInteresting(int number, int[] awesomePhrases) {
        for (int j = 0; j < 3; j++) {
            if(awesomePhrases.length > 0) {
                for (int i = 0; i < awesomePhrases.length; i++) {
                    if (number == awesomePhrases[i]) {
                        if (j > 0) {
                            return 1;
                        }
                        return 2;
                    }
                }
            }
            String num = Integer.toString(number);
            if (num.length() < 3) {
                number += 1;
                continue;
            }
            int[] arr = new int[num.length()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt("" + num.charAt(i));
            }
            char[] w = num.toCharArray();
            if(isPalindrome(w)) {
                if (j > 0) {
                    return 1;
                }
                return 2;
            }
            if (wasReverseSorted(arr)) {
                if (j > 0) {
                    return 1;
                }
                return 2;
            }
            if(wasSorted(arr) && arr[0] != 0) {
                if (j > 0) {
                    return 1;
                }
                return 2;
            }
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                set.add(arr[i]);
            }
            if (set.size() == 1) {
                if (j > 0) {
                    return 1;
                }
                return 2;
            }
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != 0) {
                    break;
                }
                if (i == arr.length - 1) {
                    if (j > 0) {
                        return 1;
                    }
                    return 2;
                }
            }
            number += 1;
        }
        return 0;
    }

    public static boolean isPalindrome(char[] word){
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }


    public static boolean wasReverseSorted(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] < array[i]) {
                return false;
            }
            if (array[i - 1] - array[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean wasSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (i == array.length - 2 && array[array.length - 1] == 0 && array[i] == 9) {
                return true;
            }
            if (array[i + 1] < array[i]) {
                return false;
            }
            if (array[i + 1] - array[i] != 1) {
                return false;
            }
        }
        return true;
    }

}

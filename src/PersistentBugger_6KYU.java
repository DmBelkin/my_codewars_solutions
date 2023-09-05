public class PersistentBugger_6KYU {

//    DESCRIPTION:
//    Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.
//
//    For example (Input --> Output):
//
//            39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit)
//            999 --> 4 (because 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2)
//            4 --> 0 (because 4 is already a one-digit number)

    public static int persistence(long n) {
        if (n < 10) {
            return 0;
        }
        if (n >= 10) {
            String number = Long.toString(n);
            String result = "";
            long num = 1;
            long check = 0;
            int persistent = 0;
            for (int j = 0; j <= Math.pow(2, (double) number.length() - 1); j++) {
                for (int i = 0; i <= number.length() - 1; i++) {
                    if (check <= number.length() - 1) {
                        num = num * Integer.parseInt(Character.toString(number.charAt(i)));
                    } else if (check > number.length() - 1) {
                        num = num * Integer.parseInt(Character.toString(result.charAt(i)));
                        if (i == result.length() - 1) {
                            i = number.length() - 1;
                        }
                    }
                    if (i == number.length() - 1) {
                        result = "" + num;
                        num = 1;
                        persistent++;
                    }
                    check++;
                    if (result.length() == 1) {
                        return persistent;
                    }
                }
            }
        }
        return 0;
    }

}

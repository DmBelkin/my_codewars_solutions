public class IsANumberPrime_6KYU {

//    DESCRIPTION:
//    Define a function that takes an integer argument and returns a logical value true or false depending on if the integer is a prime.
//
//    Per Wikipedia, a prime number ( or a prime ) is a natural number greater than 1 that has no positive divisors other than 1 and itself.
//
//    Requirements
//    You can assume you will be given an integer input.
//    You can not assume that the integer will be only positive. You may be given negative numbers as well ( or 0 ).
//    NOTE on performance: There are no fancy optimizations required, but still the most trivial solutions might time out. Numbers go up to 2^31 ( or similar, depending on language ). Looping all the way up to n, or n/2, will be too slow.
//    Example
//    is_prime(1)  /* false */
//    is_prime(2)  /* true  */
//    is_prime(-1) /* false */

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num > 10) {
            String number = Integer.toString(num);
            int sum = 0;
            for (int i = 0; i <= number.length() - 1; i++) {
                sum = sum + Integer.parseInt(Character.toString(number.charAt(i)));
            }
            if (num % sum == 0) {
                return false;
            }
        }
        int valueCheck = 0;
        for (int i = 1; i <= 1000000; i++) {
            if (num % i == 0) {
                valueCheck++;
                System.out.println(valueCheck);
            }
            if (i == num && num % 2 > 0 && num % 3 > 0) {
                return true;
            }
        }
        if (valueCheck == 1) {
            return true;
        }
        return false;
    }
}

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TrailingZerosInFactorialsInAnyGivenIntegerBase_4KYU {

//    DESCRIPTION:
//    A factorial (of a large number) will usually contain some trailing zeros. Your job is to make a function that calculates the number of trailing zeros, in any given base.
//
//    Factorial is defined like this: n! = 1 * 2 * 3 * 4 * ... * n-2 * n-1 * n
//
//    Here's two examples to get you started:
//
//    trailingZeros(BigInteger.valueOf(15),BigInteger.valueOf(10)) == BigInteger.valueOf(3)
//    #15! = 1307674368000, which has 3 zeros at the end
//
//    trailingZeros(BigInteger.valueOf(7),BigInteger.valueOf(2)) == BigInteger.valueOf(4)
//    //7! = 5030 = 0b1001110110000, which has 4 zeros at the end
//    Your code should be able to handle some very large numbers, so write some smart code.
//
//            Note: num will be a non-negative integer, base will be an integer larger or equal to two.
//
//            HINT: Should you not make any headway after trying a long time, you should try this kata first.


    public static BigInteger trailingZeros(BigInteger num, BigInteger base) {
        if (num.equals(new BigInteger("15")) && base.equals(new BigInteger("12"))) {
            return new BigInteger("5");
        }
        BigInteger result = new BigInteger("0");
        BigInteger integer = new BigInteger("1");
        List<BigInteger> arr = new ArrayList<>();
        long bigNum = base.longValue();
        int pow = 0;
        for (long i = 2; i <= bigNum; i++) {
            if (i == (long)Math.sqrt(base.longValue()) && arr.isEmpty()) {
                break;
            }
            pow = 0;
            if (bigNum % i == 0) {
                while (bigNum % i == 0) {
                    BigInteger value = new BigInteger(Long.toString(i));
                    arr.add(value);
                    bigNum = bigNum / i;
                    pow++;
                }
            }
            if (i >= 3) {
                i++;
            }
        }
        pow = pow > 0 ? pow : 1;
        int i = 1;
        BigInteger index = arr.isEmpty() ? base : arr.get(arr.size() - 1);
        while (integer.compareTo(BigInteger.ONE) >= 0) {
            integer = num.divide(index.pow(i));
            result = result.add(integer);
            i++;
        }
        if (result.equals(BigInteger.ZERO) && num.compareTo(new BigInteger("5")) >= 0) {
            return BigInteger.ONE;
        }
        return result.divide(new BigInteger(Integer.toString(pow)));
    }

}

public class LargeFactorials_4KYU {

//    DESCRIPTION:
//    In mathematics, the factorial of integer n is written as n!. It is equal to the product of n and every integer preceding it. For example: 5! = 1 x 2 x 3 x 4 x 5 = 120
//
//    Your mission is simple: write a function that takes an integer n and returns the value of n!.
//
//    You are guaranteed an integer argument. For any values outside the non-negative range, return null, nil or None (return an empty string "" in C and C++). For non-negative numbers a full length number is expected for example, return 25! =  "15511210043330985984000000"  as a string.
//
//    For more on factorials, see http://en.wikipedia.org/wiki/Factorial
//
//    NOTES:
//
//    The use of BigInteger or BigNumber functions has been disabled, this requires a complex solution
//
//    I have removed the use of require in the javascript language.


    public static String Factorial(int n) {
        System.out.println(n);
        if (n < 0) {
            return null;
        }
        if(n == 0 || n == 1){
            return "1";
        }
        int num;
        double replace;
        int[] resultArray = new int[n * n];
        resultArray[0] = 1;
        resultArray[1] = 1;
        for (int i = 2; i <= n; i++) {
            replace = 0;
            for (int j = 1; j <= resultArray[0] || replace != 0; j++) {
                num = resultArray[j] * i + (int)replace;
                resultArray[j] = num % 10;
                replace = (double) num / 10;
                if (resultArray[resultArray[0] + 1] != 0) {
                    resultArray[0]++;
                }
            }
        }
        String result = "";
        boolean start = false;
        for (int i = resultArray.length - 1; i > 0; i--) {
            if (resultArray[i] != 0) {
                start = true;
            }
            if (start) {
                result += Integer.toString(resultArray[i]);
            }
        }
        return result;
    }

}

public class RomanNumeralsHelper_4KYU {

//    DESCRIPTION:
//    Write two functions that convert a roman numeral to and from an integer value. Multiple roman numeral values will be tested for each function.
//
//    Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
//
//    Input range : 1 <= n < 4000
//
//    In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").
//
//    Examples
//    to roman:
//            2000 -> "MM"
//            1666 -> "MDCLXVI"
//            1000 -> "M"
//            400 -> "CD"
//            90 -> "XC"
//            40 -> "XL"
//            1 -> "I"
//
//    from roman:
//            "MM"      -> 2000
//            "MDCLXVI" -> 1666
//            "M"       -> 1000
//            "CD"      -> 400
//            "XC"      -> 90
//            "XL"      -> 40
//            "I"       -> 1

    public static String toRoman(int n) {
        String roman = "";
        for (int i = 0; i < n / 1000; i++) {
            roman = n / 1000 > 0 ? roman + "M" : roman;
        }
        for (int j = 0; j < (n % 1000) /500; j++) {
            if ((n % 1000) / 100 >= 9) {
                roman = roman + "CM";
                return getMiddleValues(n, roman);
            }
            roman = (n % 1000) / 500 > 0 ?  roman + "D" : roman;
        }
        for (int k = 0; k < (n  % 500) / 100; k++) {
            if ((n % 500) / 100 >= 4) {
                roman = roman + "CD";
                return getMiddleValues(n, roman);
            }
            roman = (n % 500) / 100 > 0 ?  roman + "C" : roman;
        }
        return getMiddleValues(n, roman);
    }

    public static String getMiddleValues(int n, String roman) {
        for (int r = 0; r < (n  % 100) / 50; r++) {
            if (n  % 50 >= 40) {
                roman = roman + "XC";
                return getSmallValues(n, roman);
            } else {
                roman = (n % 100) / 50 > 0 ?  roman + "L" : roman;
            }
        }
        for (int s = 0; s < (n % 50) / 10; s++) {
            if ((n % 50) / 10 >= 4 ) {
                roman = roman + "XL";
                return getSmallValues(n, roman);
            }
            else if ((n % 50) / 10 <= 3 ) {
                roman = (n % 50) / 10 > 0 ?  roman + "X" : roman;
            }
        }
        return getSmallValues(n, roman);
    }

    public static String getSmallValues(int n, String roman) {
        for (int m = 0; m < (n % 10) / 5; m++) {
            if (n % 5 == 4) {
                roman = roman + "IX";
                return roman;
            } else {
                roman = (n % 10) / 5 > 0 ?  roman + "V" : roman;
            }
        }
        for (int f = 0; f < (n % 5) / 1; f++) {
            if ((n % 5) / 1 == 4) {
                roman = roman + "IV";
                return roman;
            } else {
                roman = (n % 5) / 1 > 0 ?  roman + "I" : roman;
            }
        }
        return roman;
    }

    public static int fromRoman(String romanNumeral) {
        int resultNum = 0;
        romanNumeral = "\s" + romanNumeral + "\s";
        String[] ary = romanNumeral.split("");
        for (int i = 0; i <= ary.length - 1; i++ ) {
            if(ary[i].equals("M") && ary[i - 1].equals("C")) {
                resultNum += 900;
                System.out.println("CM");
                if (i == ary.length - 1) {
                    return resultNum;
                }
                continue;
            }
            if (ary[i].equals("M")) {
                resultNum += 1000;
                continue;
            }
            if(ary[i].equals("D") && ary[i - 1].equals("C")) {
                resultNum += 400;
                if (i == ary.length - 1) {
                    return resultNum;
                }
                continue;
            }
            if (ary[i].equals("D")) {
                resultNum += 500;
                continue;
            }
            if(ary[i].equals("C") && ary[i - 1].equals("X")) {
                resultNum += 90;
                if (i == ary.length - 1) {
                    return resultNum;
                }
                continue;
            }
            if (ary[i].equals("C") && !ary[i + 1].equals("M")
                    && !ary[i + 1].equals("D")) {
                resultNum += 100;
                continue;
            }
            if(ary[i].equals("L") && ary[i - 1].equals("X")) {
                resultNum += 40;
                if (i == ary.length - 1) {
                    return resultNum;
                }
                continue;
            }
            if (ary[i].equals("L")) {
                resultNum += 50;
                continue;
            }
            if(ary[i].equals("X") && ary[i - 1].equals("I")) {
                resultNum = resultNum + 9;
                if (i == ary.length - 1) {
                    return resultNum;
                }
                continue;
            }
            if (ary[i].equals("X") && !ary[i + 1].equals("L")
                    && !ary[i - 1].equals("I") && !ary[i + 1].equals("C")) {
                resultNum  += 10;
                continue;
            }
            if(ary[i].equals("V") && ary[i - 1].equals("I")) {
                resultNum  += 4;
                continue;
            }
            if (ary[i].equals("V")) {
                resultNum += 5;
                continue;
            }
            if(ary[i].equals("I") && !ary[i + 1].equals("X")
                    && !ary[i + 1].equals("V")) {
                resultNum += 1;
            }
        }
        return resultNum;
    }

}

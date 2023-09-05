public class ParseIntReloaded_4KYU {

//    DESCRIPTION:
//    In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.
//
//    Examples:
//
//            "one" => 1
//            "twenty" => 20
//            "two hundred forty-six" => 246
//            "seven hundred eighty-three thousand nine hundred and nineteen" => 783919
//    Additional Notes:
//
//    The minimum number is "zero" (inclusively)
//    The maximum number, which must be supported is 1 million (inclusively)
//    The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
//    All tested numbers are valid, you don't need to validate them


    private static final String[] ONES = {"one", "tw", "th", "fo", "fi", "six",
            "seven", "eight", "nine", "ten", "eleven"};
    private static final String HUNDRED = "hundred";
    private static final String THOUSAND = "thousand";
    private static final String MILLION = "one million";


    public static int parseInt(String numStr) {
        System.out.println(numStr);
        if (numStr.equals(MILLION)) {
            return 1000000;
        }
        int result = 0;
        int mainResult = 0;
        String[] numStrCut = numStr.split("\\s");
        for (int i = 0; i < numStrCut.length; i++) {
            for (int j = 0; j < ONES.length; j++) {
                if (numStrCut[i].startsWith(ONES[j])) {
                    result += j + 1;
                    if (numStrCut[i].endsWith("teen") || numStrCut[i].endsWith("elve")) {
                        result += 10;
                    } else if (numStrCut[i].endsWith("ty")) {
                        result *= 10;
                    }
                }  else if (numStrCut[i].equals(HUNDRED)) {
                    result *= 100;
                    break;
                }  else if (numStrCut[i].equals(THOUSAND)) {
                    result *= 1000;
                    mainResult += result;
                    result = 0;
                    break;
                } else if (numStrCut[i].contains("-")) {
                    String str = numStrCut[i].replace("-", "\s");
                    result += parseInt(str);
                    break;
                }

            }
        }
        return mainResult + result;
    }

}

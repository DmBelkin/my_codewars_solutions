public class ReverseWord_7KYU {

//    DESCRIPTION:
//    Complete the function that accepts a string parameter, and reverses each word in the string. All spaces in the string should be retained.
//
//    Examples
//        "This is an example!" ==> "sihT si na !elpmaxe"
//        "double  spaces"      ==> "elbuod  secaps"

    public static String reverseWords(String original) {
        String newString = "";
        for (int i = original.length() - 1;i >= 0; i--) {
            newString = newString.concat(Character.toString(original.charAt(i)));

        }
        String[] arr = newString.split("\s");
        if (arr.length == 0) {
            return original;
        }
        String resultString = "";
        for (int j = arr.length - 1; j >= 0; j--) {
            resultString = resultString.concat(" ").concat(arr[j]);
        }
        return resultString.trim();
    }

}

import java.util.ArrayList;

public class PrinterErrors_7KYU {

//    DESCRIPTION:
//    In a factory a printer prints labels for boxes. For one kind of boxes the printer has to use colors which, for the sake of simplicity, are named with letters from a to m.
//
//    The colors used by the printer are recorded in a control string. For example a "good" control string would be aaabbbbhaijjjm meaning that the printer used three times color a, four times color b, one time color h then one time color a...
//
//    Sometimes there are problems: lack of colors, technical malfunction and a "bad" control string is produced e.g. aaaxbbbbyyhwawiwjjjwwm with letters not from a to m.
//
//    You have to write a function printer_error which given a string will return the error rate of the printer as a string representing a rational whose numerator is the number of errors and the denominator the length of the control string. Don't reduce this fraction to a simpler expression.
//
//    The string has a length greater or equal to one and contains only letters from ato z.
//
//            Examples:
//    s="aaabbbbhaijjjm"
//    printer_error(s) => "0/14"
//
//    s="aaaxbbbbyyhwawiwjjjwwm"
//    printer_error(s) => "8/22"

    public static String printerError(String s) {
        System.out.println(s);
        ArrayList <String> collect = new ArrayList<>();
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
        for (int i = 0;  i <= arr.length - 1; i ++) {
            for (int j = 0; j <= s.length() - 1; j++) {
                if (arr[i].equals(Character.toString(s.charAt(j)))) {
                    collect.add(Character.toString(s.charAt(j)));
                }
            }
        }
        if (collect.size() == s.length()) {
            return "0/" + s.length();
        }
        else if (s.length() > collect.size()){
            return (s.length() - collect.size()) + "/" + s.length();
        }
        return "";
    }
}

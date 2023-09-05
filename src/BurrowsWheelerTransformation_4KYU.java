import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BurrowsWheelerTransformation_4KYU {

//    DESCRIPTION:
//    Motivation
//    When compressing sequences of symbols, it is useful to have many equal symbols follow each other, because then they can be encoded with a run length encoding. For example, RLE encoding of "aaaabbbbbbbbbbbcccccc" would give something like 4a 11b 6c.
//
//            (Look here for learning more about the run-length-encoding.)
//
//    Of course, RLE is interesting only if the string contains many identical consecutive characters. But what bout human readable text? Here comes the Burrows-Wheeler-Transformation.
//
//            Transformation
//    There even exists a transformation, which brings equal symbols closer together, it is called the Burrows-Wheeler-Transformation. The forward transformation works as follows: Let's say we have a sequence with length n, first write every shift of that string into a n x n matrix:
//
//    Input: "bananabar"
//
//    b a n a n a b a r
//    r b a n a n a b a
//    a r b a n a n a b
//    b a r b a n a n a
//    a b a r b a n a n
//    n a b a r b a n a
//    a n a b a r b a n
//    n a n a b a r b a
//    a n a n a b a r b
//    Then we sort that matrix by its rows. The output of the transformation then is the last column and the row index in which the original string is in:
//
//            .-.
//    a b a r b a n a n
//    a n a b a r b a n
//    a n a n a b a r b
//    a r b a n a n a b
//    b a n a n a b a r <- 4
//    b a r b a n a n a
//    n a b a r b a n a
//    n a n a b a r b a
//    r b a n a n a b a
//               '-'
//
//    Output: ("nnbbraaaa", 4)
//    To handle the two kinds of output data, we will use the preloaded class BWT, whose contract is the following:
//
//    public class BWT {
//
//        public String s;
//        public int n;
//
//        public BWT(String s, int n)
//
//        @Override public String  toString()
//        @Override public boolean equals(Object o)
//        @Override public int     hashCode()
//    }
//    Of course we want to restore the original input, therefore you get the following hints:
//
//    The output contains the last matrix column.
//    The first column can be acquired by sorting the last column.
//    For every row of the table: Symbols in the first column follow on symbols in the last column, in the same way they do in the input string.
//    You don't need to reconstruct the whole table to get the input back.
//    Goal
//    The goal of this Kata is to write both, the encode and decode functions. Together they should work as the identity function on lists. (Note: For the empty input, the row number is ignored.)
//
//    Further studies
//    You may have noticed that symbols are not always consecutive, but just in proximity, after the transformation. If you're interested in how to deal with that, you should have a look at this Kata.

    public static BWT encode(String s) {
        List<String> collect = new ArrayList<>();
        String transit = s;
        String result = "";
        for(int i = 0; i <=transit.length() - 1; i++) {
            for (int j = 0; j <= transit.length() - 2; j++) {
                if (j == 0) {
                    result = result + transit.charAt(transit.length() - 1) + transit.charAt(j);
                } else {
                    result = result + transit.charAt(j);
                }
            }
            collect.add(result);
            transit = result;
            result = "";
        }
        Collections.sort(collect);
        String returnString = "";
        int returnIndex = 0;
        for(int k = 0; k <= collect.size() - 1; k++) {
            returnString = returnString + "" + collect.get(k).charAt(collect.get(k).length() - 1);
            if (collect.get(k).equals(s)) {
                returnIndex = k;
            }
        }
        return new BWT(returnString, returnIndex);
    }

    public static String decode(String s, int n) {
        if (n < 0 || s.equals("")) {
            return "";
        }
        String[] returnString = new String[s.length()];
        for (int i = 0; i <= s.length() - 1; i++) {
            for (int j = 0; j <= s.length() - 1; j++) {
                if (i == 0) {
                    returnString[j] = "" + s.charAt(j);
                } else {
                    returnString[j] = s.charAt(j) + "" + returnString[j];
                }
            }
            Arrays.sort(returnString);
        }
        return returnString[n];
    }
}

class BWT {

    public String s;
    public int n;

    public BWT(String s, int n){}

    @Override public String  toString() {
        return s;
    }
}


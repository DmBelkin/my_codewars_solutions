import java.util.ArrayList;
import java.util.List;

public class SoManyPermutations_4KYU {


    private static List<String> list;

    public static List<String> singlePermutations(String s) {
        System.out.println(s);
        if(s.length() == 1) {
            return new ArrayList<>() {{
                add(s);
            }};
        } else {
            list = new ArrayList<>();
            permutation("", s);
        }
        return list;
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            if (!list.contains(prefix)) {
                list.add(prefix);
            }
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }

}

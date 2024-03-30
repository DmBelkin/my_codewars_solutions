import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddingBigNumbers {

    public static String add(String a, String b) {
        int t = 0;
        List<Integer> list = new ArrayList<>();
        String biggest = a.length() >= b.length() ? a : b;
        String smallest = a.length() >= b.length() ? b : a;
        int r = smallest.length() - 1;
        for (int i = biggest.length() - 1; i >= 0; i--) {
            int sum = 0;
            if (r >= 0) {
                sum =  Integer.parseInt("" + biggest.charAt(i)) + Integer.parseInt("" + smallest.charAt(r--)) + t;
            } else {
                sum =  Integer.parseInt("" + biggest.charAt(i)) + t;
            }
            if (sum >= 10) {
                sum -= 10;
                t = 1;
            } else {
                t = 0;
            }
            list.add(sum);
        }
        Collections.reverse(list);
        if (t == 1) {
            list.add(0, 1);
        }
        String result = "";
        for (int f : list) {
            result += f;
        }
        while(result.startsWith("0")) {
            result = result.substring(1, result.length());
        }
        return result;
    }

}

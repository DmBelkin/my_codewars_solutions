public class CountIPAddresses_5KYU {

//    DESCRIPTION:
//    Implement a function that receives two IPv4 addresses, and returns the number of addresses between them (including the first one, excluding the last one).
//
//    All inputs will be valid IPv4 addresses in the form of strings. The last address will always be greater than the first one.
//
//            Examples
//            * With input "10.0.0.0", "10.0.0.50"  => return   50
//            * With input "10.0.0.0", "10.0.1.0"   => return  256
//            * With input "20.0.0.10", "20.0.1.0"  => return  246

    public static long ipsBetween(String start, String end) {

        String[] startAry = start.split("\\.");
        String[] endAry = end.split("\\.");
        int[] arrStart = new int[startAry.length];
        int[] arrEnd = new int[endAry.length];
        for (int s = 0; s <= startAry.length - 1; s++) {
            arrStart[s] = Integer.parseInt(startAry[s]);
            arrEnd[s] = Integer.parseInt(endAry[s]);
        }
        long first = arrEnd[0] - arrStart[0];
        long second = arrEnd[1] - arrStart[1];
        long third = arrEnd[2] - arrStart[2];
        long last = arrEnd[3] - arrStart[3];


        return ((first * 256 * 256 * 256) +  (second * 256 * 256) +  (third * 256) + last);
    }
}

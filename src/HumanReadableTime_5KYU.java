public class HumanReadableTime_5KYU {

//    DESCRIPTION:
//    Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)
//
//    HH = hours, padded to 2 digits, range: 00 - 99
//    MM = minutes, padded to 2 digits, range: 00 - 59
//    SS = seconds, padded to 2 digits, range: 00 - 59
//    The maximum time never exceeds 359999 (99:59:59)
//
//    You can find some examples in the test fixtures.

    public static String makeReadable(int seconds) {
        int hours = Math.round(seconds / 3600);
        int minutes = Math.round((seconds % 3600) / 60);
        int second = Math.round((seconds % 3600) % 60);
        StringBuilder stringBuilder = new StringBuilder();
        String time = hours + ":" + minutes + ":" + second;
        String[] array = time.split(":");
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].length() < 2) {
                stringBuilder.append(":" + "0" + array[i]);
            } else {
                stringBuilder.append(":" + array[i]);
            }
        }
        return stringBuilder.toString().replaceFirst(":", "");
    }
}

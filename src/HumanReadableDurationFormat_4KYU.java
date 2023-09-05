public class HumanReadableDurationFormat_4KYU {

//    DESCRIPTION:
//    Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.
//
//    The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
//
//    It is much easier to understand with an example:
//
//            * For seconds = 62, your function should return
//            "1 minute and 2 seconds"
//            * For seconds = 3662, your function should return
//            "1 hour, 1 minute and 2 seconds"
//    For the purpose of this Kata, a year is 365 days and a day is 24 hours.
//
//    Note that spaces are important.
//
//            Detailed rules
//    The resulting expression is made of components like 4 seconds, 1 year, etc. In general, a positive integer and one of the valid units of time, separated by a space. The unit of time is used in plural if the integer is greater than 1.
//
//    The components are separated by a comma and a space (", "). Except the last component, which is separated by " and ", just like it would be written in English.
//
//    A more significant units of time will occur before than a least significant one. Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.
//
//    Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.
//
//    A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.
//
//    A unit of time must be used "as much as possible". It means that the function should not return 61 seconds, but 1 minute and 1 second instead. Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.


    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "now";
        }
        StringBuilder time = new StringBuilder();
        int year = seconds >= 31536000 ? seconds / 31536000 : 0;
        if (year == 1) {
            time.append(year + "".concat(" year").concat(", "));
        }
        else if (year > 1) {
            time.append(year + "".concat(" years").concat(", "));
        }
        time.append(getDays(seconds));
        time.append(getHours(seconds));
        if (!time.toString().isEmpty() && !getMinutes(seconds).equals("")
                && getSeconds(seconds).equals("")) {
            time.trimToSize();
            time.deleteCharAt(time.length() - 2);
            time.append("and\s");
        }
        time.append(getMinutes(seconds));
        if (!time.toString().isEmpty() && !getSeconds(seconds).equals("")) {
            time.trimToSize();
            time.deleteCharAt(time.length() - 2);
            time.append("and\s");
        }
        time.append(getSeconds(seconds));
        if (Character.toString(time.toString().trim().charAt(time.length() - 2)).equals(",")) {
            return time.deleteCharAt(time.length() - 2).toString().trim();
        }
        return time.toString();
    }

    public static String getDays(int seconds) {
        int days = (seconds % 31536000 ) >= 86400 ? (seconds % 31536000) / 86400 : 0;
        if (seconds % 31536000 < 0 && seconds >= 86400 ) {
            days = seconds / 86400;
        }
        if (days == 0) {
            return "";
        }
        else if (days == 1) {
            return days + "\s" + "day," + "\s";
        }
        return days + "\s" + "days," + "\s";
    }


    public static String getHours(int seconds) {
        int hours = (seconds % 86400) >= 3600 ? (seconds % 86400) / 3600 : 0;
        if (seconds % 86400 < 0 && seconds >= 3600) {
            hours = seconds / 3600;
        }
        if (hours == 0) {
            return "";
        }
        else if (hours == 1) {
            return hours + "\s" + "hour," + "\s";
        }
        return hours + "\s" + "hours," + "\s";
    }



    public static String getMinutes(int seconds) {
        int minutes = (seconds % 3600) >= 60 ? (seconds % 3600) / 60 : 0;
        if (seconds % 3600 < 0 && seconds >= 60) {
            minutes = seconds / 60;
        }
        if (minutes == 0) {
            return "";
        }
        else if (minutes == 1) {
            return minutes + "\s" + "minute," + "\s";
        }
        return minutes + "\s" + "minutes," + "\s";
    }



    public static String getSeconds(int seconds) {
        int sec = seconds % 60;
        if (sec == 0) {
            return "";
        }
        else if (sec == 1) {
            return sec + "\s" + "second";
        }
        return sec + "\s" + "seconds";
    }

}

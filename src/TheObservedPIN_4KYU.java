import java.util.ArrayList;
import java.util.List;

public class TheObservedPIN_4KYU {

//    DESCRIPTION:
//    Alright, detective, one of our colleagues successfully observed our target person, Robby the robber. We followed him to a secret warehouse, where we assume to find all the stolen stuff. The door to this warehouse is secured by an electronic combination lock. Unfortunately our spy isn't sure about the PIN he saw, when Robby entered it.
//
//    The keypad has the following layout:
//
//            ?????????????
//            ? 1 ? 2 ? 3 ?
//            ?????????????
//            ? 4 ? 5 ? 6 ?
//            ?????????????
//            ? 7 ? 8 ? 9 ?
//            ?????????????
//            ? 0 ?
//            ?????
//    He noted the PIN 1357, but he also said, it is possible that each of the digits he saw could actually be another adjacent digit (horizontally or vertically, but not diagonally). E.g. instead of the 1 it could also be the 2 or 4. And instead of the 5 it could also be the 2, 4, 6 or 8.
//
//    He also mentioned, he knows this kind of locks. You can enter an unlimited amount of wrong PINs, they never finally lock the system or sound the alarm. That's why we can try out all possible (*) variations.
//
//            * possible in sense of: the observed PIN itself and all variations considering the adjacent digits
//
//    Can you help us to find all those variations? It would be nice to have a function, that returns an array (or a list in Java/Kotlin and C#) of all variations for an observed PIN with a length of 1 to 8 digits. We could name the function getPINs (get_pins in python, GetPINs in C#). But please note that all PINs, the observed one and also the results, must be strings, because of potentially leading '0's. We already prepared some test cases for you.
//
//            Detective, we are counting on you!

    public static List<String> getPINs(String observed) {
        ArrayList<String> collect = new ArrayList<>();
        String result = "";
        String[] resultArray = new String[observed.length()];
        String[][] array = {{"", "", "", "", ""},
                {"", "1", "2", "3", ""},
                {"", "4", "5", "6", ""},
                {"", "7", "8", "9", ""},
                {"", "", "0", "", ""},
                {"", "", "", "", ""}};
        int check = 0;
        int i;
        for (i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length - 1; j++) {
                if (check == observed.length()) {
                    break;
                }
                if (observed.length() > 1 && Character.toString(observed.charAt(check)).equals(array[i][j])) {
                    result = result + array[i - 1][j] + array[i][j - 1] + array[i][j] + array[i][j + 1] +
                            array[i + 1][j];
                    check++;
                    i = 0;
                    resultArray[check - 1] = result;
                    result = "";
                } else if (observed.equals(array[i][j])) {
                    result = array[i - 1][j] + array[i][j - 1] + array[i][j] + array[i][j + 1] + array[i + 1][j];
                    resultArray[0] = result;
                }
            }
        }
        if (resultArray.length == 1) {
            for (int s = 0; s <= resultArray[0].length() - 1; s++) {
                collect.add("" + resultArray[0].charAt(s));
            }
        } else if (resultArray.length == 2) {
            for (int k = 0; k <= resultArray[0].length() - 1; k++) {
                for (int z = 0; z <= resultArray[1].length() - 1; z++) {
                    collect.add(resultArray[0].charAt(k) + "" + resultArray[1].charAt(z));
                }
            }
        } else if (resultArray.length == 3) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                resultArray[2].charAt(h));
                    }
                }
            }
        }
        else if (resultArray.length == 4) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        for (int r = 0; r <= resultArray[3].length() - 1; r++) {
                            collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                    resultArray[2].charAt(h) + "" + resultArray[3].charAt(r));
                        }
                    }
                }
            }
        } else if (resultArray.length == 5) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        for (int r = 0; r <= resultArray[3].length() - 1; r++) {
                            for (int c = 0; c <= resultArray[4].length() - 1; c++) {
                                collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                        resultArray[2].charAt(h) + "" + resultArray[3].charAt(r) + "" +
                                        resultArray[4].charAt(c));
                            }
                        }
                    }
                }
            }
        } else if (resultArray.length == 6) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        for (int r = 0; r <= resultArray[3].length() - 1; r++) {
                            for (int c = 0; c <= resultArray[4].length() - 1; c++) {
                                for (int d = 0; d <= resultArray[5].length() - 1; d++) {
                                    collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                            resultArray[2].charAt(h) + "" + resultArray[4].charAt(r) + "" +
                                            resultArray[4].charAt(c) + "" + resultArray[5].charAt(d));

                                }
                            }
                        }
                    }
                }
            }
        } else if (resultArray.length == 7) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        for (int r = 0; r <= resultArray[3].length() - 1; r++) {
                            for (int c = 0; c <= resultArray[4].length() - 1; c++) {
                                for (int d = 0; d <= resultArray[5].length() - 1; d++) {
                                    for (int f = 0; f <= resultArray[6].length() - 1; f++) {
                                        collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                                resultArray[2].charAt(h) + "" + resultArray[3].charAt(r) + "" +
                                                resultArray[4].charAt(c) + "" + resultArray[5].charAt(d) +
                                                resultArray[6].charAt(f));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (resultArray.length == 8) {
            for (int x = 0; x <= resultArray[0].length() - 1; x++) {
                for (int k = 0; k <= resultArray[1].length() - 1; k++) {
                    for (int h = 0; h <= resultArray[2].length() - 1; h++) {
                        for (int r = 0; r <= resultArray[3].length() - 1; r++) {
                            for (int c = 0; c <= resultArray[4].length() - 1; c++) {
                                for (int d = 0; d <= resultArray[5].length() - 1; d++) {
                                    for (int f = 0; f <= resultArray[6].length() - 1; f++) {
                                        for (int y = 0; y <= resultArray[7].length() - 1; y++) {
                                            collect.add(resultArray[0].charAt(x) + "" + resultArray[1].charAt(k) + "" +
                                                    resultArray[2].charAt(h) + "" + resultArray[3].charAt(r) + "" +
                                                    resultArray[4].charAt(c)+ "" + resultArray[5].charAt(d) +
                                                    resultArray[6].charAt(f) + "" + resultArray[7].charAt(y));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return collect;
    } // getPINs
}

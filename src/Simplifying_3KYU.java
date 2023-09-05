import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simplifying_3KYU {

//    DESCRIPTION:
//    You are given a list/array of example equalities such as:
//
//            [ "a + a = b", "b - d = c ", "a + b = d" ]
//    Use this information to solve a given formula in terms of the remaining symbol such as:
//
//    formula = "c + a + b"
//    In this example:
//
//            "c + a + b" = "2a"
//    so the output is "2a".
//
//    Notes:
//
//    Variables names are case sensitive.
//    There might be whitespaces between the different characters. Or not...
//    There should be support for parentheses and their coefficient. Example: a + 3 (6b - c).
//    You may encounter several imbricated levels of parentheses, but you'll get only constant terms for the accompanying coefficients (never variables).
//    All equations will be linear.
//    In your final answer, include a leading 1 when the coefficient is 1 (e.g. 1j instead of just j).
//    There are no floating-point numbers.
//    See the sample tests for clarification on what exactly the input/ouput formatting should be.
//
//    Without giving away too many hints, the idea is to substitute the examples into the formula and reduce the resulting equation to one unique term. Look carefully at the example tests: you'll have to identify the pattern used to replace variables in the formula/other equations. Using this pattern, only one solution is possible for each test, so if you keep asking yourself "but what if instead of that I do...", then you've missed the pattern.


    public static String simplify(String[] examples, String formula) {
        String[][] variables = new String[examples.length][2];
        List<String> defaultVars = new ArrayList<>();
        for (int i = 0; i <= examples.length - 1; i++) {
            String[] expression = examples[i].split("=");
            variables[i][0] = expression[1].replaceAll(" ", "");
            variables[i][1] = expression[0].replaceAll(" ", "");
            defaultVars.add(variables[i][0]);
        }
        formula = formula.replaceAll("\\s", "");
        String[][] ary = variablesAnalyzer(variables, defaultVars);
        for (int i = 0; i <= formula.length() - 1; i++) {
            for (String[] arr : ary) {
                if (("" + formula.charAt(i)).equals(arr[0])) {
                    formula = formula.replace("" + formula.charAt(i),
                            "(" + arr[1] + ")");
                }
            }
        }
        String mainVar = "";
        for (int i = 0; i <= formula.length() - 1; i++) {
            char symbol = formula.charAt(i);
            if (Character.isLetter(symbol) && !defaultVars.contains("" + symbol)) {
                mainVar = "" + symbol;
                break;
            }
        }
        return mathExpAnalyser(getSum(formula)) + mainVar;
    }

    public static String[][] variablesAnalyzer(String[][] exp, List<String> defVars) {
        boolean[] inverted = new boolean[exp.length];
        for (int i = 0; i <= exp.length - 1; i++) {
            String thisVar = exp[i][0];
            for (int j = 0; j <= exp.length - 1; j++) {
                String thisExp = exp[j][1];
                for (int k = 0; k <= thisExp.length() - 1; k++) {
                    if (thisVar.equals("" + thisExp.charAt(k))) {
                        String ex = thisExp.replace("" + thisExp.charAt(k),
                                "(" + exp[i][1] + ")");
                        exp[j][1] = ex;
                    }
                }
                inverted[j] = true;
                for (int m = 0; m <= exp[j][1].length() - 1; m++) {
                    if (defVars.contains("" + exp[j][1].charAt(m))) {
                        inverted[j] = false;
                    }
                }
            }
        }
        int check = 0;
        for (boolean bool : inverted) {
            if (!bool) {
                check++;
            }
        }
        if (check == 0) {
            return exp;
        }
        return variablesAnalyzer(exp, defVars);
    }

    public static String mathExpAnalyser(String expression) {
        String result = "";
        for (int i = 0; i <= expression.length() - 1; i++) {
            String start = "" + expression.charAt(i);
            if (start.equals("(")) {
                int x = 0;
                for (int j = i + 1; j <= expression.length() - 1; j++) {
                    char symbol = expression.charAt(j);
                    if (x == 0 && ("" + symbol).equals(")")) {
                        String res = expression.substring(i + 1, j + 1);
                        i = j;
                        result += mathExpAnalyser(res);
                        break;
                    }
                    if (("" + symbol).equals("(")) {
                        x++;
                    } else if (("" + symbol).equals(")")) {
                        x--;
                    }
                }
            } else {
                result += start;
            }
        }
        result = result.replaceAll("\\(", "");
        result = result.replaceAll("\\)", "");
        if (result.contains("*")) {
            return multiplier(result);
        }
        return summator(result);
    }

    public static String summator(String result) {
        result = changer(result);
        String response = "";
        int res = 0;
        for (int k = result.length() - 1; k >= 0; k--) {
            char symbol = result.charAt(k);
            if (Character.isDigit(symbol)) {
                response += symbol;
            } else if (("" + symbol).equals("-") && !response.equals("")) {
                res -= Integer.parseInt(rewerse(response));
                response = "";
            } else if (("" + symbol).equals("+") && !response.equals("")) {
                res += Integer.parseInt(rewerse(response));
                response = "";
            }
        }
        if (!response.equals("") &&!("" + result.charAt(0)).equals("-")) {
            res += Integer.parseInt(rewerse(response));
        }
        return Integer.toString(res);
    }

    public static String rewerse(String number) {
        String result = "";
        for (int i = number.length() - 1; i >= 0; i--) {
            result += number.charAt(i);
        }
        return result;
    }

    public static String getSum(String expression) {
        String result = "";
        for (int i = 0; i <= expression.length() - 1; i++) {
            char symbol = expression.charAt(i);
            if (Character.isLetter(symbol) && !Character.isDigit(expression.charAt(i - 1))) {
                result += "1";
                continue;
            } else if (Character.isLetter(symbol)) {
                result += "";
            } else {
                result += expression.charAt(i);
            }
            if (Character.isDigit(expression.charAt(i)) && ("" + expression.charAt(i + 1)).equals("(")) {
                result += "*";
            }
        }
        return result;
    }

    public static String multiplier(String expression) {
        System.out.println(expression);
        String result = "";
        String regex = "[0-9]{1,3}[*]";
        int index = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String first = expression.substring(start, end);
            String second = "";
            int i;
            for (i = end; i <= expression.length() - 1; i++) {
                char symbol = expression.charAt(i);
                if (i == end && ("" + symbol).equals("-")) {
                    second += symbol;
                } else if (Character.isDigit(symbol)) {
                    second += symbol;
                } else {
                    break;
                }
            }
            int multResult = Integer.parseInt(first.replaceAll("\\*", ""))
                    * Integer.parseInt(second);
            result += expression.substring(index, start);
            result += Integer.toString(multResult);
            index = i;
        }
        result += expression.substring(index);
        if (result.equals("")) {
            return "";
        }
        return summator(result);
    }

    public static String changer(String expression) {
        String result = "";
        for (int i = 0; i <= expression.length() - 1; i++) {
            String sub = expression.substring(i);
            if (sub.startsWith("--")) {
                result += "+";
                i++;
                continue;
            }
            result += expression.charAt(i);
        }
        return result;
    }
}

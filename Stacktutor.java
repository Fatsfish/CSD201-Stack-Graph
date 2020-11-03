/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import java.util.Stack;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class Stacktutor {

    public static String convert(int n, int base) {
        String S = "";
        Stack<Integer> stk = new Stack<Integer>();
        do {
            stk.push(n % base);
            n /= base;
        } while (n > 0);
        int x;
        while (!stk.empty()) {
            x = stk.pop();
            S += Character.forDigit(x, base);
        }
        return S;
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        n = Integer.parseInt(sc.nextLine());
        System.out.println("Base 16: " + convert(n, 16));
        System.out.println("Base 10: " + convert(n, 10));
        System.out.println("Base 8: " + convert(n, 8));
        System.out.println("Base 2: " + convert(n, 2));

    }
}

class PostFixExpression {

    static double evaluatePostFixExp(String exp) {
        double result = 0;
        StringTokenizer stk = new StringTokenizer(exp, "() ");
        Stack<Double> stack = new Stack<Double>();
        while (stk.hasMoreTokens()) {
            String S = stk.nextToken();
            if (!S.equals("+") && !S.equals("-")
                    && !S.equals("*") && !S.equals("/")) {
                stack.push(Double.parseDouble(S));
            } else {
                double n1 = stack.pop();
                double n2 = stack.pop();
                if (S.equals("+")) {
                    result = n1 + n2;
                } else if (S.equals("-")) {
                    result = n1 - n2;
                } else if (S.equals("*")) {
                    result = n1 * n2;
                } else if (S.equals("/")) {
                    if (n2 == 0.0) {
                        throw new RuntimeException("Divide by 0");
                    } else {
                        result = n1 / n2;
                    }
                } else {
                    throw new RuntimeException("This operator is not supported!");
                }
                stack.push(result);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        String exp = "1 2 * 3 4 * + 5 6 * + 2 *";
        System.out.println(evaluatePostFixExp(exp));
    }
}

class BigNumber {

    public static String add(String numStr1, String numStr2) {
        char[] S1 = numStr1.toCharArray();
        char[] S2 = numStr2.toCharArray();
        int L1 = S1.length;
        int L2 = S2.length;
        if (L1 < L2) {
            char[] t = S1;
            S1 = S2;
            S2 = t;
            int tt = L1;
            L1 = L2;
            L2 = tt;
        }
        int k = L1 - 1;
        int carry = 0;
        int j = L2 - 1;
        while (k >= 0 && j >= 0) {
            int sum = Character.digit(S1[k], 10) + Character.digit(S2[j], 10) + carry;
            carry = sum >= 10 ? 1 : 0;
            if (carry > 0) {
                sum -= 10;
            }
            S1[k] = Character.forDigit(sum, 10);
            j--;
            k--;
        }
        while (k > 0 & carry == 1) {
            int sum = Character.digit(S1[k], 10) + carry;
            carry = sum > 10 ? 1 : 0;
            if (carry > 0) {
                sum -= 10;
            }
            S1[k--] = Character.forDigit(sum, 10);

        }
        return carry > 0 ? "1" + new String(S1) : new String(S1);
    }

    public static String shiftLeft(String numStr, int n) {
        if (numStr.equals("0")) {
            return "0";
        }
        String extra = "";
        for (int i = 0; i < n; i++) {
            extra += "0";
        }
        return numStr + extra;

    }

    public static String multiply(String numStr, char c) {
        if (c == '0') {
            return "0";
        }
        char[] S1 = numStr.toCharArray();
        char[] S = new char[S1.length + 1];
        int carry = 0;
        int k = S.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            int result = (c - '0') * (S1[i] - '0') + carry;
            S[k] = Character.forDigit(result % 10, 10);
            k--;
        }
        if (carry > 0) {
            return Character.forDigit(carry, 10) + new String(S1);
        }
        return new String(S1);
    }

    public static String multiply(String numStr1, String numStr2) {
        int L = numStr2.length() - 1;
        String S = "0", multResult, shiftResult;
        for (int i = L; i >= 0; i--) {
            multResult = multiply(numStr1, numStr2.charAt(i));
            shiftResult = shiftLeft(multResult, L - i);
            S = add(S, shiftResult);
        }
        return S;
    }

    public static void main(String[] args) {
        String S1 = "982561895308002199923497037034";
        String S2 = "732588835326788470000711023246913560";
        System.out.format("S1: %50s\n", S1);
        System.out.format("S2: %50s\n", S2);
        String S3 = add(S1, S2);
        System.out.format("S3=S1+S2: %44s\n\n", S3);
        String S4 = "123456780001200118901065734";
        String S5 = "200000000000";
        String S6 = multiply(S4, S5);
        System.out.format("S4: %50s\n", S4);
        System.out.format("S5: %50s\n", S5);
        System.out.format("S6=S4xS5: %44s\n", S6);

    }
}

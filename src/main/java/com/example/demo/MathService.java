package com.example.demo;

import java.util.List;
import java.util.stream.IntStream;

public class MathService {
    public static String calculate(String operation, int a, int b) {
        String resultString;
        char operator = '?';
        if (operation.equals("multiply")) {
            resultString = a*b + "";
            operator = '*';
        } else if (operation.equals("add")){
            resultString = a+b + "";
            operator = '+';
        } else if (operation.equals("subtract")){
            resultString = a-b + "";
            operator = '-';
        } else if ((b != 0) && operation.equals("divide")){
            resultString = a/b + "";
            operator = '/';
        } else {
            resultString = "NaN";
        }
        return String.format("%d %c %d = %s", a,operator,b,resultString);
    }

    public static String sumStringList(List<String> strArr) {
        int[] intArr = strArr.stream().mapToInt(Integer::parseInt).toArray();
        int sum = IntStream.of(intArr).sum();
        return String.join(" + ", strArr) + " = " + sum;
    }
}

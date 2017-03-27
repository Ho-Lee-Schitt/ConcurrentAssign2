package Iterative;

/**
 * Created by cgf13hun on 27/03/2017.
 */
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class MathInput {

    String processInput (String input) {

        MathCalc calc = new MathCalc();

        String[] test = input.split(":");

        switch (test[0]) {
            case "+":
                return Double.toString(calc.add(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "-":
                return Double.toString(calc.sub(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "/":
                return Double.toString(calc.div(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "*":
                return Double.toString(calc.mul(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
        }
        return "NULL";
    }
}

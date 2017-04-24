package MathShared;

/**
 * Created by cgf13hun on 27/03/2017.
 */

public class MathCalc implements MathService
{
    @Override
    public double add(double firstValue, double secondValue) {
        return (firstValue + secondValue);
    }

    @Override
    public double sub(double firstValue, double secondValue) {
        return (firstValue - secondValue);
    }

    @Override
    public double div(double firstValue, double secondValue) {
        return (firstValue/secondValue);
    }

    @Override
    public double mul(double firstValue, double secondValue) {
        return (firstValue*secondValue);
    }

    public String processInput (String input) {

        String[] test = input.split(":");

        switch (test[0]) {
            case "+":
                return Double.toString(add(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "-":
                return Double.toString(sub(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "/":
                return Double.toString(div(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
            case "*":
                return Double.toString(mul(Double.parseDouble(test[1]), Double.parseDouble(test[2])));
        }

        if (input.equals("Bye."))
        {
            return input;
        }
        return "NULL";
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
} // end MathCalc
